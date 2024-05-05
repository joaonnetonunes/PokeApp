package com.example.pokeapp.core.di

import com.example.pokeapp.data.repository.ApiRepositoryImpl
import com.example.pokeapp.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindApiRepository(
        impl: ApiRepositoryImpl
    ): ApiRepository
}
