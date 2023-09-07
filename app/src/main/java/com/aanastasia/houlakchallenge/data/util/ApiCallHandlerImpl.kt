package com.aanastasia.houlakchallenge.data.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiCallHandlerImpl @Inject constructor(
    private val dispatcher : CoroutineDispatcher
) : ApiCallHandler{

    override suspend fun <T> process(apiCall: suspend () -> Response<T>): Result<T> {
        return withContext(dispatcher) {
            try {
                val response = apiCall.invoke()
                if (response.isSuccessful) {
                    return@withContext Result.success(value = response.body()!!)
                } else {
                    return@withContext Result.failure(ApiFailure.ServiceUnavailableError())
                }
            } catch (throwable: Throwable) {
                Log.e(TAG, "process():", throwable)
                return@withContext Result.failure(ApiFailure.ExceptionError)
            }
        }
    }

    companion object {
        private const val TAG = "ApiCallHandlerImpl"
    }

}