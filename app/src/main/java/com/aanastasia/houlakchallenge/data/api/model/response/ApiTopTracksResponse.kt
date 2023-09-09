package com.aanastasia.houlakchallenge.data.api.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ApiTopTracksResponse(
    @SerializedName("tracks")
    val tracks: List<ApiTrack>,

)


