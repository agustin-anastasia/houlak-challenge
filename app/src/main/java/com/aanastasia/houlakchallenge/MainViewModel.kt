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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getAccessToken: GetAccessToken,
    private val saveAccessToken: SaveAccessToken,
) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getAccessToken()
            }
                .onSuccess {
                    saveAccessToken(it.accessToken)
                }
                .onFailure {
                    Log.e("error", it.message!!)
                }
        }
    }

}