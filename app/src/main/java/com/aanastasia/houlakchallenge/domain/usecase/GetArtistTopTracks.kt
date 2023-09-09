package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.ArtistRepository
import javax.inject.Inject

class GetArtistTopTracks @Inject constructor(
    private val artistRepository: ArtistRepository
) {

    suspend operator fun invoke(token: String, id: String) = artistRepository.getArtistTopTracks(token, id)

}