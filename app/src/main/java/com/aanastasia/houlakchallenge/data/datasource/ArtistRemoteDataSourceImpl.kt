package com.aanastasia.houlakchallenge.data.datasource

import android.util.Log
import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.data.api.model.response.toDomain
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.Artist
import javax.inject.Inject

class ArtistRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : ArtistRemoteDataSource {


    override suspend fun getArtist(id: String): Artist {
        return try {
            val response = apiService.getArtist(id)

            if(response.isSuccessful){
                val artistResponse = response.body()
                artistResponse?.toDomain()
                    ?: throw Throwable(message = "api response is null")
            } else {
                throw Throwable(
                    message = "API call failed with status code ${response.code()}"
                )
            }
        } catch (t: Throwable) {
            Log.e("getArtist", t.message, t)
            throw t
        }
    }

    override suspend fun searchArtist(artist: String): List<ApiArtist> {
        return try {
            val response = apiService.searchArtists(artist)
            if(response.isSuccessful){
                val artistResponse = response.body()
                artistResponse?.toDomain()
                    ?: throw Throwable(message = "API response is null")
            } else {
                throw Throwable(
                    message = "API call failed with status code ${response.code()}"
                )
            }
        } catch (t: Throwable) {
            Log.e("searchArtist", t.message, t)
            throw t
        }
    }


}