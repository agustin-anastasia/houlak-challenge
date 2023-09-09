package com.aanastasia.houlakchallenge.data.di

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.aanastasia.houlakchallenge.data.api.AccessTokenApiService
import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiCallHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .apply {
                addInterceptor(httpLoggingInterceptor)
            }
            .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
        }


    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(ApiService.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccessTokenApiService(client: OkHttpClient): AccessTokenApiService {
        val newClient = client.newBuilder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder().baseUrl(AccessTokenApiService.BASE_URL)
            .client(newClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AccessTokenApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    fun provideApiCallHandler(): ApiCallHandler {
        return ApiCallHandlerImpl(
            dispatcher = Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "prefs"
    )

}