package com.ahmetocak.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.LocationPagingDataSource
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.RickAndMortyRemoteDataSource
import com.ahmetocak.rickandmorty.data.mappers.toCharacter
import com.ahmetocak.rickandmorty.data.utils.mapResponse
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import com.ahmetocak.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val rickAndMortyRemoteDataSource: RickAndMortyRemoteDataSource,
    private val locationPagingDataSource: LocationPagingDataSource
): RickAndMortyRepository {
    override suspend fun getCharacters(ids: List<Int>): Response<List<Character>> {
        return rickAndMortyRemoteDataSource.getCharacters(ids).mapResponse { it.toCharacter() }
    }

    override fun getLocations(): Flow<PagingData<Locations>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { locationPagingDataSource }
        ).flow
    }
}