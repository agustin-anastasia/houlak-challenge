package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.AccessToken
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration


data class AccessTokenResponse(
    @SerializedName("access_token")
    val accessToken : String,
    @SerializedName("token_type")
    val tokenType : String,
    @SerializedName("expires_in")
    val expiresIn: Int,
)

fun AccessTokenResponse.toDomain() = AccessToken(
    accessToken = accessToken,
    tokenType = tokenType,
    expiresIn = expiresIn
)