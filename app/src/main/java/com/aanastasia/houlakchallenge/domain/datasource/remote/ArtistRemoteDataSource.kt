package com.aanastasia.houlakchallenge.domain.datasource.remote

import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.domain.model.Artist
import com.aanastasia.houlakchallenge.domain.model.Track

interface ArtistRemoteDataSource {

    suspend fun getArtist(token: String, id: String) : Artist

    suspend fun searchArtist(token: String, artist: String) : List<Artist>

    suspend fun getArtistTopTracks(token: String, id: String) : List<Track>

}