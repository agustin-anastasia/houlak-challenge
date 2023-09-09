package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.Track
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration

data class ApiTrack(
    @SerializedName("name")
    val name: String,
    @SerializedName("duration_ms")
    val duration: Int,
)

fun ApiTrack.toDomain() = Track(
    name = name,
    duration = duration,
)
