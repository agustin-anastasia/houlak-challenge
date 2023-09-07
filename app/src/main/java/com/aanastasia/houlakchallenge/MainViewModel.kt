package com.aanastasia.houlakchallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aanastasia.houlakchallenge.domain.model.toPresentation
import com.aanastasia.houlakchallenge.domain.usecase.GetAccessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccessToken: GetAccessToken,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.NonAuthorized)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        Log.v("---- MainViewVodel", "---- MainViewModel")
        viewModelScope.launch{
            runCatching { getAccessToken() }
                .onSuccess {
                    Log.v("AGUS" , it.accessToken)
                    _uiState.update { MainUiState.Authorized }
                }
                .onFailure {
                    Log.v("agustin", "FAILUREEEEEEEEEEE")
                }
        }
    }

    private fun setAuthToken(){

    }
}

sealed interface MainUiState {
    object Authorized : MainUiState
    object NonAuthorized : MainUiState

}