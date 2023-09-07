package com.aanastasia.houlakchallenge.domain.model

import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.presentation.model.PAccessToken
import java.time.Duration

data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)

fun AccessToken.toData() = AccessTokenResponse(
    accessToken = accessToken,
    tokenType = tokenType,
    expiresIn = expiresIn
)

fun AccessToken.toPresentation() = PAccessToken(
    accessToken = accessToken
)
