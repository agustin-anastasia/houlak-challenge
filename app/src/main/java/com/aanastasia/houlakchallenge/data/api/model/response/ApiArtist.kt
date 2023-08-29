package com.aanastasia.houlakchallenge.data.api.model.response

import com.aanastasia.houlakchallenge.domain.model.Artist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiArtist(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("followers")
    val followers: Int,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("images")
    val image: List<ImageUrl>,
    @SerialName("popularity")
    val popularity: Int,
)

@Serializable
data class ImageUrl(
    @SerialName("height")
    val height : Int,
    @SerialName("url")
    val url : String,
    @SerialName("width")
    val width : Int,
)

fun ApiArtist.toDomain() = Artist(
    id = this.id,
    name = this.name,
    imageUrl = this.image.toList()[0].url
)
