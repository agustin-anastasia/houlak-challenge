package com.aanastasia.houlakchallenge.presentation.model

import com.aanastasia.houlakchallenge.data.api.model.response.ImageUrl
import com.aanastasia.houlakchallenge.domain.model.Artist

data class PArtist(
    val id: String,
    val name: String,
    val followers: Int,
    val imageUrl: ArrayList<ImageUrl>,
    val popularity: Int,
    val genres: List<String>,
)

fun Artist.toPresentation() = PArtist(
    id = id,
    name = name,
    followers = followers,
    imageUrl = imageUrl,
    popularity = popularity,
    genres = genres,
)