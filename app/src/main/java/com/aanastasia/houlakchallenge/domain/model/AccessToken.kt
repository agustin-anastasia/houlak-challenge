package com.aanastasia.houlakchallenge.domain.model

import java.time.Duration

data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)