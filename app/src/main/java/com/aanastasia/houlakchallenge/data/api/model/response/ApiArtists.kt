package com.aanastasia.houlakchallenge.data.api.model.response


import com.aanastasia.houlakchallenge.domain.model.Artist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiArtists(
    @SerialName("items")
    val items: List<ApiArtist>,
    @SerialName("total")
    val total: Int
)

fun ApiArtists.toDomain(): List<ApiArtist> {
    return items.map {artist ->
        ApiArtist(
            id = artist.id,
            name = artist.name,
            image = artist.image,
            followers = artist.followers,
            genres = artist.genres,
            popularity = artist.popularity
        )
    }.sortedByDescending { it.popularity }
}