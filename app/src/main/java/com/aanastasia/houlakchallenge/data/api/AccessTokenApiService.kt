package com.aanastasia.houlakchallenge.data.api

import com.aanastasia.houlakchallenge.data.api.model.request.AccessTokenRequest
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenApiService {

    companion object{
        const val BASE_URL = "https://accounts.spotify.com/api/"
        const val BASE_URL_TEST = "https://getmovies.pages.dev/details/335977/"
        const val CLIENT_ID = "5d5046fdf20e485caaf9c20cc09d26be"
        const val CLIENT_SECRET = "67fa72f4d4424718ac7d342fe062ab3c"
    }

    @FormUrlEncoded
    @POST("token")
    suspend fun getAccessToken(
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded",
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = CLIENT_ID,
        @Field("client_secret") clientSecret: String = CLIENT_SECRET,
    ): Response<AccessTokenResponse>

}