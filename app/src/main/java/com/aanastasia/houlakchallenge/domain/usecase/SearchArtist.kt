package com.aanastasia.houlakchallenge.domain.usecase

import com.aanastasia.houlakchallenge.domain.repository.ArtistRepository
import javax.inject.Inject

class SearchArtist @Inject constructor(
    private val artistRepository: ArtistRepository
) {

    suspend operator fun invoke(artist: String) = artistRepository.searchArtist(artist)

}