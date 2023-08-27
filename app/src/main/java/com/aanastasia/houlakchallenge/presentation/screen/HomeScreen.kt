package com.aanastasia.houlakchallenge.presentation.screen

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    //onArtistDetail: () -> Unit, //todo ir al detalle de un artista
) {

    HomeScreenContent(
        uiState = viewModel.uiState,
        onArtistSearch = viewModel::onArtistSearch,
        onArtistSelected = viewModel::onArtistSelected,
    )

}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onArtistSearch: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {

    Surface {

    }

}
