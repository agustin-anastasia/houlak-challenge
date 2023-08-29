package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import javax.inject.Inject

class GetAccessToken @Inject constructor(
    private val accessTokenRepository: AccessTokenRepository
) {

    suspend operator fun invoke() = accessTokenRepository.getAccessToken()

}