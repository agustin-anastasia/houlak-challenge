package com.aanastasia.houlakchallenge.domain.model

import com.aanastasia.houlakchallenge.data.api.model.response.ImageUrl

data class Artist(
    val id: String,
    val name: String,
    val followers: Int,
    val imageUrl: List<ImageUrl>,
    val popularity: Int,
)