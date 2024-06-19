package com.ahmetocak.rickandmorty.di

import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.LocationPagingDataSource
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.RickAndMortyRemoteDataSource
import com.ahmetocak.rickandmorty.data.repository.RickAndMortyRepositoryImpl
import com.ahmetocak.rickandmorty.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        locationPagingDataSource: LocationPagingDataSource,
        rickAndMortyRemoteDataSource: RickAndMortyRemoteDataSource
    ): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(rickAndMortyRemoteDataSource, locationPagingDataSource)
    }
}