package com.aanastasia.houlakchallenge.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aanastasia.houlakchallenge.domain.usecase.GetToken
import com.aanastasia.houlakchallenge.domain.usecase.SearchArtist
import com.aanastasia.houlakchallenge.presentation.model.PArtist
import com.aanastasia.houlakchallenge.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchArtists: SearchArtist,
    private val getToken: GetToken,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())

    init {
        viewModelScope.launch {
            runCatching {
                getToken().collect {
                    uiState = uiState.copy(
                        token = it,
                        loading = false
                    )
                }
            }
        }
    }

    fun onArtistSearch(artist: String) {
        viewModelScope.launch {
            runCatching {
                uiState = uiState.copy(
                    loading = true
                )
                searchArtists(uiState.token, artist)
            }
                .onSuccess {
                    val list = it.sortedByDescending { it.popularity }
                    uiState = uiState.copy(
                        artists = list.map { artist ->
                            artist.toPresentation()
                        },
                        loading = false
                    )
                }
                .onFailure {
                    throw it
                }
        }
    }

    fun onSearchTextChange(query: String) {
        uiState = uiState.copy(
            searchQuery = query,
            selectedArtist = "",
            artists = emptyList(),
        )
    }

}

data class HomeUiState(
    val token: String = "",
    val searchQuery: String = "",
    val selectedArtist: String = "",
    val artists: List<PArtist> = emptyList(),
    val loading: Boolean = false
)
