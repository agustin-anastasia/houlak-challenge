package com.aanastasia.houlakchallenge.data.datasource

import com.aanastasia.houlakchallenge.data.api.AccessTokenApiService
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.data.api.model.response.toDomain
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiFailure
import com.aanastasia.houlakchallenge.domain.datasource.remote.AccessTokenRemoteSource
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import com.google.gson.Gson
import java.util.Arrays
import javax.inject.Inject


class AccessTokenRemoteSourceImpl @Inject constructor(
    private val accessTokenApiService: AccessTokenApiService,
    private val apiCallHandler: ApiCallHandler,
) : AccessTokenRemoteSource {

    override suspend fun getAccessToken(): AccessToken = try {
        val gson = Gson()
        apiCallHandler
            .process { accessTokenApiService.getAccessToken() }
            .mapCatching { response ->
                val jsonString = gson.toJson(response)
                val accessTokenResponse = gson.fromJson(jsonString, AccessTokenResponse::class.java)
                accessTokenResponse.toDomain()
            }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }

}