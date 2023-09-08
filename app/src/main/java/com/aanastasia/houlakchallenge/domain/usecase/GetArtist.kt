package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.ArtistRepository
import javax.inject.Inject

class GetArtist @Inject constructor(
    private val artistRepository: ArtistRepository
) {
    suspend operator fun invoke(token: String, id: String) = artistRepository.getArtist(token, id)
}