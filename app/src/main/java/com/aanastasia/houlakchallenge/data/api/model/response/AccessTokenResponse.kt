package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.AccessToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration

@Serializable
data class AccessTokenResponse(
    @SerialName("access_token")
    val accessToken : String,
    @SerialName("token_type")
    val tokenType : String,
    @SerialName("expires_in")
    val expiresIn: Int,
)

fun AccessTokenResponse.toDomain() = AccessToken(
    accessToken = accessToken,
    tokenType = tokenType,
    expiresIn = expiresIn
)