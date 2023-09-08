package com.aanastasia.houlakchallenge.data.di

import com.aanastasia.houlakchallenge.data.datasource.local.AccessTokenLocalDataSourceImpl
import com.aanastasia.houlakchallenge.data.datasource.AccessTokenRemoteSourceImpl
import com.aanastasia.houlakchallenge.data.datasource.ArtistRemoteDataSourceImpl
import com.aanastasia.houlakchallenge.domain.datasource.local.AccessTokenLocalDataSource
import com.aanastasia.houlakchallenge.domain.datasource.remote.AccessTokenRemoteSource
import com.aanastasia.houlakchallenge.domain.datasource.remote.ArtistRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AbstractDataModule {

    @Binds
    @Singleton
    abstract fun bindArtistRemoteDataSource(
        artistRemoteDataSourceImpl: ArtistRemoteDataSourceImpl
    ) : ArtistRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAccessTokenRemoteSource(
        accessTokenRemoteSource: AccessTokenRemoteSourceImpl
    ) : AccessTokenRemoteSource

    @Binds
    @Singleton
    abstract fun bindAccessTokenLocalDataSource(
        accessTokenLocalDataSource: AccessTokenLocalDataSourceImpl
    ): AccessTokenLocalDataSource



}