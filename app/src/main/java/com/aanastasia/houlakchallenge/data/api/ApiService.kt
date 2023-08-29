package com.aanastasia.houlakchallenge.data.api

import com.aanastasia.houlakchallenge.data.api.model.request.AccessTokenRequest
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtist
import com.aanastasia.houlakchallenge.data.api.model.response.ApiArtists
import com.aanastasia.houlakchallenge.data.api.model.response.ApiTopTracksResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.spotify.com/v1/"
        const val TYPE = "artist"
        const val US_MARKET = "US"
    }

    @GET("search")
    suspend fun searchArtists(
        @Query("q") query : String,
        @Query("type") type: String = TYPE,
        @Query("market") market: String = US_MARKET,
    ): Response<ApiArtists>

    @GET("artists/{id}")
    suspend fun getArtist(@Path("id") artistId: String): Response<ApiArtist>

    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(
        @Path("id") artistId: String,
        @Query("market") market: String = US_MARKET
    ): Response<ApiTopTracksResponse>




}