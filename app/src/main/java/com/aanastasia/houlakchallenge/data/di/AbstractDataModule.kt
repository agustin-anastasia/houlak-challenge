package com.aanastasia.houlakchallenge.data.di

import com.aanastasia.houlakchallenge.data.datasource.ArtistRemoteDataSourceImpl
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


}