package com.aanastasia.houlakchallenge.data.datasource

import android.util.Log
import com.aanastasia.houlakchallenge.data.api.AccessTokenApiService
import com.aanastasia.houlakchallenge.data.api.model.request.AccessTokenRequest
import com.aanastasia.houlakchallenge.data.api.model.response.toDomain
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiFailure
import com.aanastasia.houlakchallenge.domain.datasource.remote.AccessTokenRemoteSource
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import javax.inject.Inject

class AccessTokenRemoteSourceImpl @Inject constructor(
    private val accessTokenApiService: AccessTokenApiService,
    private val apiCallHandler: ApiCallHandler,
) : AccessTokenRemoteSource {

    override suspend fun getAccessToken(): AccessToken = try {
        apiCallHandler
            .process { accessTokenApiService.getAccessToken() }
            .mapCatching { response -> response.toDomain() }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }

    /*override suspend fun getAccessToken(): AccessToken {
        return try {
            val response = accessTokenApiService.getAccessToken()
            if(response.isSuccessful){
                val accessTokenResponse = response.body()
                accessTokenResponse?.toDomain()
                    ?: throw Throwable(message = "API response is null")
            } else {
                throw Throwable(
                    message = "API call failed with status code ${response.code()}"
                )
            }
        } catch (t: Throwable){
            Log.e("getAccessToken", t.message, t)
            throw t
        }
    }*/

}