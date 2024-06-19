package com.ahmetocak.rickandmorty.domain.usecase.character

import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: RickAndMortyRepository) {

    /**
     * This method gets characters from the API.
     *
     * @param ids ids of the characters.
     * @return a [Response] flow of type [Character] list.
     */
    suspend operator fun invoke(ids: List<Int>): Response<List<Character>> {
        return repository.getCharacters(ids)
    }
}