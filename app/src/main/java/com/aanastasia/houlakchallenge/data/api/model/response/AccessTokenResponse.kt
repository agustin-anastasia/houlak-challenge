package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.AccessToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration

@JsonClass(generateAdapter = true)
data class AccessTokenResponse(
    @Json(name = "access_token")
    val accessToken : String,
    @Json(name = "token_type")
    val tokenType : String,
    @Json(name = "expires_in")
    val expiresIn: Int,
)

fun AccessTokenResponse.toDomain() = AccessToken(
    accessToken = accessToken,
    tokenType = tokenType,
    expiresIn = expiresIn
)