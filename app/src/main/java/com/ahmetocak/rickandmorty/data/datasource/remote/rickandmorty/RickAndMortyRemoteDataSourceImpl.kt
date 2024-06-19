package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty

import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.api.RickAndMortyApi
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character.CharacterDto
import javax.inject.Inject

class RickAndMortyRemoteDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApi
): RickAndMortyRemoteDataSource {
    override suspend fun getCharacters(ids: List<Int>): Response<List<CharacterDto>> {
        return try {
            Response.Success(data = api.getCharacters(ids))
        } catch (e: Exception) {
            Response.Error(message = e.message ?: "Something went wrong please again later!!")
        }
    }
}