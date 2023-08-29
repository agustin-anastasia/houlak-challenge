package com.aanastasia.houlakchallenge.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs", 0)

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString("access_token", token)
    }

    fun getAccessToken() : String {
        return sharedPreferences.getString(ACCESS_TOKEN, null).toString()
    }

    companion object{
        const val ACCESS_TOKEN = "access_token"
    }
}

