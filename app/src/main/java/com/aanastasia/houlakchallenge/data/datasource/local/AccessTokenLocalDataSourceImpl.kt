package com.aanastasia.houlakchallenge.data.datasource.local


import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import com.aanastasia.houlakchallenge.domain.datasource.local.AccessTokenLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccessTokenLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AccessTokenLocalDataSource {

    override suspend fun saveToken(token: String) {
        Log.v("SAVE TOKEN DATASOURCE", token)
        withContext(Dispatchers.IO){
            val key = stringPreferencesKey(ACCESS_TOKEN)
            dataStore.edit { preferences ->
                preferences[key] = token
            }
        }
    }

    override suspend fun getToken(): Flow<String> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(ACCESS_TOKEN)].orEmpty()
    }

    companion object{
        private const val ACCESS_TOKEN = "access_token"
    }


}