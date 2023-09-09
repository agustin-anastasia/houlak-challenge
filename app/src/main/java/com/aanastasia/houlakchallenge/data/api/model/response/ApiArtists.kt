package com.aanastasia.houlakchallenge.data.api.model.response


import com.google.gson.annotations.SerializedName

data class ApiArtists(
    @SerializedName("artists")
    val artists: ApiArtistItems
)

