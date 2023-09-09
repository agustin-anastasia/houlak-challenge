package com.aanastasia.houlakchallenge.presentation.model

import com.aanastasia.houlakchallenge.domain.model.Track
import java.time.Duration

data class PTrack(
    val name : String,
    val duration : Int,
)

fun Track.toPresentation() = PTrack(
    name = name,
    duration = duration,
)