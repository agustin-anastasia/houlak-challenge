package com.aanastasia.houlakchallenge.data.api

import com.aanastasia.houlakchallenge.data.api.model.request.AccessTokenRequest
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenApiService {

    companion object{
        const val BASE_URL = "https://accounts.spotify.com/api/token/"
    }

    @Headers("Content-Type: application/x-www-form-urlencoded", "authorization: basic")
    @POST("https://accounts.spotify.com/api/token/")
    suspend fun getAccessToken(
        @Body request: AccessTokenRequest
    ): Response<AccessTokenResponse>

}