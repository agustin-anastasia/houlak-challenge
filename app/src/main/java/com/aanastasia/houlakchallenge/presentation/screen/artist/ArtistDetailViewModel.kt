package com.aanastasia.houlakchallenge.presentation.screen.artist

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aanastasia.houlakchallenge.domain.model.Track
import com.aanastasia.houlakchallenge.domain.usecase.GetArtist
import com.aanastasia.houlakchallenge.domain.usecase.GetArtistTopTracks
import com.aanastasia.houlakchallenge.domain.usecase.GetToken
import com.aanastasia.houlakchallenge.presentation.model.PArtist
import com.aanastasia.houlakchallenge.presentation.model.PTrack
import com.aanastasia.houlakchallenge.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtist: GetArtist,
    private val getArtistTopTracks: GetArtistTopTracks,
    private val getToken: GetToken,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var uiState by mutableStateOf(ArtistDetailUiState())

    val artistId: String = savedStateHandle.get<String>("id")!!

    init {
        viewModelScope.launch {
            runCatching {
                getToken().collect {
                    uiState = uiState.copy(
                        token = it,
                    )
                    getArtistDetail(artistId)
                    getArtistTopTracks(artistId)
                }
            }
        }
    }

    private fun getArtistTopTracks(id: String){
        viewModelScope.launch {
            runCatching {
                getArtistTopTracks(uiState.token, id)
            }
                .onSuccess {
                    uiState = uiState.copy(
                        tracks = it.take(5).map {
                            it.toPresentation() }
                    )
                }
                .onFailure {
                    throw it
                }
        }
    }

    private fun getArtistDetail(id: String) {
        viewModelScope.launch {
            runCatching {
                getArtist(uiState.token, id)
            }
                .onSuccess {
                    uiState = uiState.copy(
                        artist = it.toPresentation(),
                    )
                }
                .onFailure {
                    throw it
                }
        }
    }

}

data class ArtistDetailUiState(
    val artistId: String = "1bAftSH8umNcGZ0uyV7LMg",
    val token: String = "",
    val artist: PArtist ?= null,
    val tracks: List<PTrack> = emptyList(),
)