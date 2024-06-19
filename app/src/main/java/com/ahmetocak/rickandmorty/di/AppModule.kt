package com.ahmetocak.rickandmorty.di

import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.api.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocationApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }
}