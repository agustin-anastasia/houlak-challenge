package com.aanastasia.houlakchallenge.data.datasource

import android.util.Log
import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.data.api.model.response.ApiSearchArtistsResponse
import com.aanastasia.houlakchallenge.data.api.model.response.toDomain
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiFailure
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import com.aanastasia.houlakchallenge.domain.model.Artist
import com.google.gson.Gson
import javax.inject.Inject

class ArtistRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiCallHandler: ApiCallHandler,
) : ArtistRemoteDataSource {

    override suspend fun getArtist(token: String, id: String): Artist = try {
        apiCallHandler
            .process { apiService.getArtist("Bearer $token", id) }
            .mapCatching { it.toDomain() }
            .getOrThrow()
    } catch (throwable: ApiFailure){
        throw throwable
    }

   /* override suspend fun getArtist(id: String): Artist {
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
    }*/

    override suspend fun searchArtist(artist: String, token: String): List<ApiArtist> {
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