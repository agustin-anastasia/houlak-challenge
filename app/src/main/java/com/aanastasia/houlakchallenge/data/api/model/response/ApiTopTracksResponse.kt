package com.aanastasia.houlakchallenge.data.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTopTracksResponse(
    @SerialName("tracks")
    val tracks: List<ApiTrack>,

)


