package com.ahmetocak.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getCharacters(ids: List<Int>): Response<List<Character>>

    /**
     * This method provide Rick And Morty locations from the API.
     */
    fun getLocations(): Flow<PagingData<Locations>>
}