package com.ahmetocak.rickandmorty.di

import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.RickAndMortyRemoteDataSource
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.RickAndMortyRemoteDataSourceImpl
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.api.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(api: RickAndMortyApi): RickAndMortyRemoteDataSource {
        return RickAndMortyRemoteDataSourceImpl(api)
    }
}