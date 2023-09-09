package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.Artist
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ApiArtist(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("followers")
    val followers: Followers,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("images")
    val image: ArrayList<ImageUrl>,
    @SerializedName("popularity")
    val popularity: Int,
)

data class ImageUrl(
    @SerializedName("height")
    val height : Int,
    @SerializedName("url")
    val url : String,
    @SerializedName("width")
    val width : Int,
)

data class Followers(
    @SerializedName("total")
    val total : Int
)

fun ApiArtist.toDomain() = Artist(
    id = id,
    name = name,
    followers = followers.total,
    imageUrl = image,
    popularity = popularity,
    genres = genres
)
