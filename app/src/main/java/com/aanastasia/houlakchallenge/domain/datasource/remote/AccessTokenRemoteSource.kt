package com.aanastasia.houlakchallenge.domain.datasource.remote

import com.aanastasia.houlakchallenge.domain.model.AccessToken

interface AccessTokenRemoteSource {

    suspend fun getAccessToken() : AccessToken

}