package com.aanastasia.houlakchallenge.data.api.model.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ApiSearchArtistsResponse(
    @SerializedName("artists")
    val artists: List<ApiArtist>,
)