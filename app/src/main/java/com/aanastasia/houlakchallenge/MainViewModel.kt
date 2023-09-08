package com.aanastasia.houlakchallenge

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aanastasia.houlakchallenge.domain.usecase.GetAccessToken
import com.aanastasia.houlakchallenge.domain.usecase.GetArtist
import com.aanastasia.houlakchallenge.domain.usecase.GetToken
import com.aanastasia.houlakchallenge.domain.usecase.SaveAccessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccessToken: GetAccessToken,
    private val getArtist: GetArtist,
    private val saveAccessToken: SaveAccessToken,
    private val getToken: GetToken,
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState())

    init {
        getAuthAccessToken()

        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                getAccessToken()
            }
                .onSuccess {
                    saveAccessToken(it.accessToken)
                    getAuthToken()
                    callGetArtist("0du5cEVh5yTK9QJze8zA0C")
                }
                .onFailure {
                    Log.e("error", it.message!!)
                }
        }
    }

    private fun getAuthAccessToken() {
        viewModelScope.launch {
            runCatching {
                getAccessToken()
            }
                .onSuccess {
                    saveAccessToken(it.accessToken)
                }
        }
    }

    private fun getAuthToken() {
        viewModelScope.launch(Dispatchers.IO) {
            getToken().collect{
                uiState = uiState.copy(
                    token = it
                )
            }
        }
    }

    private fun callGetArtist(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                Log.v("MAINVIEWMODEL",uiState.token)
                getArtist(uiState.token, id)
            }
                .onSuccess {
                    Log.v("AGUS", it.name)
                }
                .onFailure {
                    Log.v("AGUS", it.message!!)
                }
        }
    }
}

data class MainUiState(
    val token: String = ""
)