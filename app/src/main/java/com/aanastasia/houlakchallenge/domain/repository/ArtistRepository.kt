package com.aanastasia.houlakchallenge.domain.repository

import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.Artist
import com.aanastasia.houlakchallenge.domain.model.Track
import javax.inject.Inject

interface ArtistRepository {

    suspend fun getArtist(token: String, id: String) : Artist

    suspend fun searchArtist(token: String, artist: String) : List<Artist>

    suspend fun getArtistTopTracks(token: String, artist: String) : List<Track>

}

class ArtistRepositoryImpl @Inject constructor(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {


    override suspend fun getArtist(token: String, id: String) : Artist {
        return artistRemoteDataSource.getArtist(token, id)
    }

    override suspend fun searchArtist(token: String, artist: String): List<Artist> {
        return artistRemoteDataSource.searchArtist(token, artist)
    }

    override suspend fun getArtistTopTracks(token: String, artist: String): List<Track> {
        return artistRemoteDataSource.getArtistTopTracks(token, artist)
    }


}