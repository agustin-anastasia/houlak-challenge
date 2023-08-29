package com.aanastasia.houlakchallenge.domain.di

import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepository
import com.aanastasia.houlakchallenge.domain.repository.AccessTokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindAccessTokenRepository(
        accessTokenRepositoryImpl: AccessTokenRepositoryImpl
    ): AccessTokenRepository


}