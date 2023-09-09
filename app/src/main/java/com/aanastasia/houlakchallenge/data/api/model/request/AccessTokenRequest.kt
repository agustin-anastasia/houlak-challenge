package com.aanastasia.houlakchallenge.data.api.model.request

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
data class AccessTokenRequest(
    @SerialName("grant_type")
    val grant_type : String = "client_credentials",
    @SerialName("client_id")
    val client_id : String = "5d5046fdf20e485caaf9c20cc09d26be",
    @SerialName("client_secret")
    val client_secret : String = "67fa72f4d4424718ac7d342fe062ab3c",
)
