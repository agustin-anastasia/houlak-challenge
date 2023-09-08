package com.aanastasia.houlakchallenge.domain.di

import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepositoryImpl
import com.aanastasia.houlakchallenge.domain.repository.ArtistRepository
import com.aanastasia.houlakchallenge.domain.repository.ArtistRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindAccessTokenRepository(
        accessTokenRepositoryImpl: AccessTokenRepositoryImpl
    ): AccessTokenRepository

    @Binds
    @Singleton
    fun bindArtistRepository(
        artistRepositoryImpl: ArtistRepositoryImpl
    ): ArtistRepository


}