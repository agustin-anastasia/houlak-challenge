package com.aanastasia.houlakchallenge.data.di

import com.aanastasia.houlakchallenge.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
            .build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiService2(client: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(ApiService.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiService::class.java)
    }



//    @Singleton
//    @Provides
//    fun provide

}