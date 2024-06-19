package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty

import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character.CharacterDto

interface RickAndMortyRemoteDataSource {

    suspend fun getCharacters(ids: List<Int>) : Response<List<CharacterDto>>
}