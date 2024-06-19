package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.api

import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character.CharacterDto
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.location.LocationDto
import com.ahmetocak.rickandmorty.data.utils.CHARACTER_END_POINT
import com.ahmetocak.rickandmorty.data.utils.CHARACTER_ID_PATH
import com.ahmetocak.rickandmorty.data.utils.LOCATION_END_POINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET(CHARACTER_END_POINT)
    suspend fun getCharacters(
        @Path(CHARACTER_ID_PATH) ids: List<Int>
    ): List<CharacterDto>

    @GET(LOCATION_END_POINT)
    suspend fun getLocations(
        @Query("page") page: Int
    ): LocationDto
}