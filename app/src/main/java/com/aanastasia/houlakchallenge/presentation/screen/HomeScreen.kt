package com.aanastasia.houlakchallenge.presentation.screen

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    //onArtistDetail: () -> Unit, //todo navigate to artist detail screen
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

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HoulakChallengeTheme() {
        HomeScreenContent(
            uiState = HomeUiState(),
            onArtistSearch = {},
            onArtistSelected = {},
        )
    }
}
