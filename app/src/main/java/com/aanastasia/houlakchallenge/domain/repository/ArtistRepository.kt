package com.aanastasia.houlakchallenge.domain.repository

import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.Artist
import javax.inject.Inject

interface ArtistRepository {

    suspend fun getArtist(id: String) : Artist

    suspend fun searchArtist(artist: String) : List<ApiArtist>

}

class ArtistRepositoryImpl @Inject constructor(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {


    override suspend fun getArtist(id: String) : Artist {
        return artistRemoteDataSource.getArtist(id)
    }

    override suspend fun searchArtist(artist: String): List<ApiArtist> {
        return artistRemoteDataSource.searchArtist(artist)
    }

}