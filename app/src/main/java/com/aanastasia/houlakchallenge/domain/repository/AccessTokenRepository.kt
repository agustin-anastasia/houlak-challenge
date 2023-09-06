package com.aanastasia.houlakchallenge.domain.repository

import android.content.Context
import com.aanastasia.houlakchallenge.data.api.model.response.AccessTokenResponse
import com.aanastasia.houlakchallenge.data.local.SharedPreferencesManager
import com.aanastasia.houlakchallenge.domain.datasource.remote.AccessTokenRemoteSource
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface AccessTokenRepository {

    suspend fun getAccessToken() : Any

}

class AccessTokenRepositoryImpl @Inject constructor(
    private val accessTokenRemoteSource : AccessTokenRemoteSource,
) : AccessTokenRepository {

    override suspend fun getAccessToken(): Any {
        return accessTokenRemoteSource.getAccessToken()
    }


}