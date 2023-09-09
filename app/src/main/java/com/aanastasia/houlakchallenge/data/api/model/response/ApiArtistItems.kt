package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ApiArtistItems (
    @SerializedName("items")
    val items: List<ApiArtist>
)