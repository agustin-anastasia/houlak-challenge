package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToken @Inject constructor(
    private val accessTokenRepository: AccessTokenRepository
){

    suspend operator fun invoke(): Flow<String> {
        return accessTokenRepository.getToken()
    }

}