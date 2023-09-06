package com.aanastasia.houlakchallenge.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        onSearchTextChange = viewModel::onSearchTextChange
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onArtistSearch: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {

    var searchText by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = searchText,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                placeholder = {
                    Text(
                        color = Color.Gray,
                        text = "Search your favorite artist",
                    )
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = true
            ){

            }
        }
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
            onSearchTextChange = {}
        )
    }
}
