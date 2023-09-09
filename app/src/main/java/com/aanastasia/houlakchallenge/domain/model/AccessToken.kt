package com.aanastasia.houlakchallenge.domain.model

data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)