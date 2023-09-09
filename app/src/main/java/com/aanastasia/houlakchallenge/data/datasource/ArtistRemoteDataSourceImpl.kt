package com.aanastasia.houlakchallenge.data.datasource

import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.data.api.model.response.toDomain
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiFailure
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.Artist
import com.aanastasia.houlakchallenge.domain.model.Track
import javax.inject.Inject

class ArtistRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiCallHandler: ApiCallHandler,
) : ArtistRemoteDataSource {

    override suspend fun getArtist(token: String, id: String): Artist = try {
        val header = "Bearer $token"
        apiCallHandler
            .process { apiService.getArtist(header, id) }
            .mapCatching { it.toDomain() }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }

    override suspend fun searchArtist(token: String, artist: String): List<Artist> = try {
        val header = "Bearer $token"
        apiCallHandler
            .process { apiService.searchArtists(header, artist) }
            .mapCatching {
                it.artists.items.map { it.toDomain() }
            }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }

    override suspend fun getArtistTopTracks(token: String, id: String): List<Track> = try{
        val header = "Bearer $token"
        apiCallHandler
            .process { apiService.getArtistTopTracks(header, id) }
            .mapCatching {
                it.tracks.map { it.toDomain() }
            }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }


}