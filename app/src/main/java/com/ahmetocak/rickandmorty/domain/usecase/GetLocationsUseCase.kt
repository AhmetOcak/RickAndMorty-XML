package com.ahmetocak.rickandmorty.domain.usecase

import com.ahmetocak.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val rickAndMortyRepository: RickAndMortyRepository) {

    operator fun invoke() = rickAndMortyRepository.getLocations()
}