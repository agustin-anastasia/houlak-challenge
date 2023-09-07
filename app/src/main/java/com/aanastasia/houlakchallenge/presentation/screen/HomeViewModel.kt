package com.aanastasia.houlakchallenge.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())

    fun onArtistSearch(artist: String) {

    }

    fun onArtistSelected(artist: String){

    }

    fun onSearchTextChange(query: String){
        uiState = uiState.copy(
            searchQuery = query,
            selectedArtist = "",
            artists = emptyList(),
        )
    }

}


data class HomeUiState(
    val searchQuery: String = "",
    val selectedArtist: String = "",
    val artists: List<String> = emptyList(),
)