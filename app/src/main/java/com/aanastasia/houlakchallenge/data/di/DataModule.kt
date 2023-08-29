package com.aanastasia.houlakchallenge.data.di

import android.util.Log
import com.aanastasia.houlakchallenge.data.api.AccessTokenApiService
import com.aanastasia.houlakchallenge.data.api.ApiService
import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient) : ApiService {
        return Retrofit.Builder().baseUrl(ApiService.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccessTokenApiService(client: OkHttpClient) : AccessTokenApiService {
        val newClient = client.newBuilder()
            .addInterceptor{ chain ->
                val request = chain.request().newBuilder()
                    .build()
                    chain.proceed(request)
            }
            .build()

        return Retrofit.Builder().baseUrl(AccessTokenApiService.BASE_URL).client(newClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(AccessTokenApiService::class.java)
    }

}