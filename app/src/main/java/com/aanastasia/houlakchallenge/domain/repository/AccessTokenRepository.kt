package com.aanastasia.houlakchallenge.domain.repository

import com.aanastasia.houlakchallenge.domain.datasource.local.AccessTokenLocalDataSource
import com.aanastasia.houlakchallenge.domain.datasource.remote.AccessTokenRemoteSource
import com.aanastasia.houlakchallenge.domain.model.AccessToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AccessTokenRepository {

    suspend fun getAccessToken() : AccessToken
    suspend fun saveToken(token: String)
    suspend fun getToken(): Flow<String>

}

class AccessTokenRepositoryImpl @Inject constructor(
    private val accessTokenRemoteSource : AccessTokenRemoteSource,
    private val accessTokenLocalDataSource: AccessTokenLocalDataSource
) : AccessTokenRepository {

    override suspend fun getAccessToken(): AccessToken {
        return accessTokenRemoteSource.getAccessToken()
    }

    override suspend fun saveToken(token: String) {
        accessTokenLocalDataSource.saveToken(token)
    }

    override suspend fun getToken(): Flow<String> {
        return accessTokenLocalDataSource.getToken()
    }


}