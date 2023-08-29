package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.Track
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration

@Serializable
data class ApiTrack(
    @SerialName("name")
    val name: String,
    @SerialName("duration_ms")
    val duration: Duration,
)

fun ApiTrack.toDomain() = Track(
    name = name,
    duration = duration,
)
