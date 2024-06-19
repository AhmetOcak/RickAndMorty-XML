package com.ahmetocak.rickandmorty.data.mappers

import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character.CharacterDto
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.location.ResultsDto
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.model.character.CharacterLocation
import com.ahmetocak.rickandmorty.domain.model.character.Origin
import com.ahmetocak.rickandmorty.domain.model.location.Locations

/**
 * This method maps the [CharacterDto] list to [Character] list.
 */
fun List<CharacterDto>.toCharacter(): List<Character> {
    val characters: ArrayList<Character> = arrayListOf()

    forEach {
        characters.add(
            Character(
                id = it.id ?: 0,
                name = it.name ?: "",
                gender = it.gender ?: "",
                location = CharacterLocation(
                    locationName = it.location?.locationName ?: "",
                    url = it.location?.url ?: ""
                ),
                url = it.url ?: "",
                type = it.type ?: "",
                image = it.image ?: "",
                created = it.created ?: "",
                origin = Origin(
                    name = it.origin?.name ?: "",
                    url = it.origin?.url ?: ""
                ),
                species = it.species ?: "",
                status = it.status ?: "",
                episode = it.episode
            )
        )
    }

    return characters
}

fun List<ResultsDto>.toResults(): List<Locations> {
    return this.map {
        Locations(
            id = it.id,
            name = it.name ?: "unknown",
            type = it.type ?: "",
            dimension = it.dimension ?: "",
            residents = it.residents,
            url = it.url ?: "",
            createdDate = it.createdDate ?: ""
        )
    }
}