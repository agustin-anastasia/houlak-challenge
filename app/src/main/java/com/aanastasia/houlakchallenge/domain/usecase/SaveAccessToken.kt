package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import javax.inject.Inject

class SaveAccessToken @Inject constructor(
    private val accessTokenRepository: AccessTokenRepository
) {

    suspend operator fun invoke(token: String) {
        accessTokenRepository.saveToken(token)
    }

}