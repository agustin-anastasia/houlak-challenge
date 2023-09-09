package com.aanastasia.houlakchallenge.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aanastasia.houlakchallenge.R
import com.aanastasia.houlakchallenge.presentation.model.PArtist
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme
import com.aanastasia.houlakchallenge.presentation.theme.HoulakViolet

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onArtistSelected: (String) -> Unit,
) {
    HomeScreenContent(
        uiState = viewModel.uiState,
        onArtistSearch = viewModel::onArtistSearch,
        onArtistSelected = onArtistSelected,
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
    val listState = rememberLazyListState()
    Surface(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    0F to Color.Black.copy(alpha = 1F),
                    1F to HoulakViolet.copy(alpha = 0.5F),
                    1F to Color.Black.copy(alpha = 0F)
                )
            )
            .fillMaxSize(),
        color = Color.Black.copy(alpha = 0.2F)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onArtistSearch(searchText) }
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                placeholder = {
                    Text(
                        color = Color.Gray,
                        text = "Search artist",
                    )
                },
                singleLine = true
            )
        }
        if (uiState.searchQuery.isEmpty() || uiState.searchQuery.isBlank()  ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.artist_empty_state_placeholder),
                    contentDescription = "empty state image",
                    tint = Color.LightGray
                )
                Text(
                    text = "Welcome!",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.LightGray
                )
                Text(
                    text = "Search for your favorites artists",
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.LightGray
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                userScrollEnabled = true,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                //todo custom artist item
                items(uiState.artists) {
                    ArtistItem(artist = it, onClickItem = onArtistSelected)
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
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

@Composable
fun ArtistItem(
    artist: PArtist,
    onClickItem: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                onClickItem(artist.id)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (artist.imageUrl.size > 0) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .fillMaxSize(),
                model = artist.imageUrl[0].url,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "empty state image",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(80.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp),
        ) {
            Text(text = artist.name, style = MaterialTheme.typography.subtitle1, color = Color.White)
            Text(
                text = "Popularity: ${artist.popularity}",
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun artistItemPreview() {
    HoulakChallengeTheme() {
        ArtistItem(
            artist = PArtist(
                id = "sadkljashfalk;32545nml23",
                name = "Michael Jackson",
                followers = 9872384,
                imageUrl = arrayListOf(),
                popularity = 100,
                genres = emptyList()
            ),
            onClickItem = {}
        )
    }
}

