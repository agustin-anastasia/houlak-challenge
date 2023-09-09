package com.aanastasia.houlakchallenge.presentation.screen.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme
import com.aanastasia.houlakchallenge.presentation.theme.HoulakViolet

@Composable
fun ArtistDetailScreen(
    viewModel: ArtistDetailViewModel = hiltViewModel(),
) {
    ArtistDetailScreenContent(
        uiState = viewModel.uiState,
    )
}


@Composable
fun ArtistDetailScreenContent(
    uiState: ArtistDetailUiState,
) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    0F to Color.Black.copy(alpha = 1F),
                    1F to HoulakViolet.copy(alpha = 0.5F),
                    1F to Color.Black.copy(alpha = 0F)
                )
            )
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.artist != null && uiState.artist.imageUrl.size > 0) {
                AsyncImage(
                    model = uiState.artist?.imageUrl?.get(0)?.url,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth(0.8F)
                        .aspectRatio(1.8F),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            uiState.artist?.let {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h1
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            if (uiState.artist != null && uiState.artist.genres.size > 0) {
                Text(text = "Genres:",
                    style = MaterialTheme.typography.h3)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    uiState.artist.let { it1 ->
                        items(it1.genres) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "${uiState.artist?.name} top tracks: ",
                style = MaterialTheme.typography.h3)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(uiState.tracks) {
                    Text(text = it.name)
                }
            }

        }
    }
}

@Preview
@Composable
fun ArtistDetailPreview() {
    HoulakChallengeTheme {
        ArtistDetailScreenContent(
            uiState = ArtistDetailUiState()
        )
    }
}