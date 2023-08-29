package com.aanastasia.houlakchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.aanastasia.houlakchallenge.MainUiState
import com.aanastasia.houlakchallenge.MainViewModel
import com.aanastasia.houlakchallenge.presentation.theme.HoulakChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: MainUiState by mutableStateOf(MainUiState.NonAuthorized)

        setContent {

            val state = viewModel.uiState.collectAsState()
            HoulakChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (state.value == MainUiState.Authorized){
                        Text(text = "Android")
                    } else {
                        Text(text = "NOT ANDROID")
                    }
                }
            }
        }
    }
}