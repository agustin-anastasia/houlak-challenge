package com.aanastasia.houlakchallenge.domain.datasource.remote

import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.domain.model.Artist

interface ArtistRemoteDataSource {

    suspend fun getArtist(id: String) : Artist

    suspend fun searchArtist(artist: String) : List<ApiArtist>


}