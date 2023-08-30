package com.aanastasia.houlakchallenge.data.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiCallHandlerImpl @Inject constructor(
    private val dispatcher : CoroutineDispatcher
) : ApiCallHandler{

    override suspend fun <T> process(apiCall: suspend () -> Response<T>): Result<T> =
        withContext(dispatcher) {
            try {
                val response = apiCall()
                if (response.isSuccessful){
                    Result.success(value = response.body()!!)
                } else {
                    Result.failure(
                        exception = ApiFailure.Service(
                            errorCode = response.code(),
                            errorMessage = response.message()
                        )
                    )
                }
            } catch (throwable: Throwable) {
                Log.e("ApiCallHandlerImpl", "process()",throwable)
                Result.failure(exception = throwable)
            }
        }

}