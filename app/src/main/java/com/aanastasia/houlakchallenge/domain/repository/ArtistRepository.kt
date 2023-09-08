package com.aanastasia.houlakchallenge.domain.repository

import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.Artist
import javax.inject.Inject

interface ArtistRepository {

    suspend fun getArtist(token: String, id: String) : Artist

    suspend fun searchArtist(token: String, artist: String) : List<ApiArtist>

}

class ArtistRepositoryImpl @Inject constructor(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {


    override suspend fun getArtist(token: String, id: String) : Artist {
        return artistRemoteDataSource.getArtist(token, id)
    }

    override suspend fun searchArtist(token: String, artist: String): List<ApiArtist> {
        return artistRemoteDataSource.searchArtist(token, artist)
    }

}