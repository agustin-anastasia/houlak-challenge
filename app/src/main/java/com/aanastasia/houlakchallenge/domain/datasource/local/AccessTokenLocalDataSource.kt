package com.aanastasia.houlakchallenge.domain.datasource.local

import kotlinx.coroutines.flow.Flow

interface AccessTokenLocalDataSource {

    suspend fun saveToken(token: String)

    suspend fun getToken(): Flow<String>

}