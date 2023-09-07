package com.aanastasia.houlakchallenge.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme
import com.aanastasia.houlakchallenge.presentation.theme.Purple80

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
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Red,
                ),
                textStyle = MaterialTheme.typography.h2,
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearchTextChange(it)
                },
                keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        ),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                placeholder = {
                    Text(
                        color = Color.Gray,
                        text = "Search your favorite artist",
                    )
                },
                singleLine = true,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = true
            ){
                //todo custom artist item
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
