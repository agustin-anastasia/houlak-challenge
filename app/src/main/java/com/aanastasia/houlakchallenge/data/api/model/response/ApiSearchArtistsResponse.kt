package com.aanastasia.houlakchallenge.data.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSearchArtistsResponse(
    @SerialName("artists")
    val artists: List<ApiArtist>,
)