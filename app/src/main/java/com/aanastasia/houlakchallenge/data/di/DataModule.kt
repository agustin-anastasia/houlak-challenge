package com.aanastasia.houlakchallenge.data.di

import android.util.Log
import com.aanastasia.houlakchallenge.data.api.AccessTokenApiService
import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.data.util.ApiCallHandler
import com.aanastasia.houlakchallenge.data.util.ApiCallHandlerImpl
import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /* @Singleton
     @Provides
     fun provideOkHttpClient(): OkHttpClient {
         return OkHttpClient.Builder().addInterceptor(
             HttpLoggingInterceptor().apply {
                 level = HttpLoggingInterceptor.Level.BODY
             }
         ).build()
     }*/

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

}