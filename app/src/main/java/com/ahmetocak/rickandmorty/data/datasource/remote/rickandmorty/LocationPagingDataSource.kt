package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.api.RickAndMortyApi
import com.ahmetocak.rickandmorty.data.mappers.toResults
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import javax.inject.Inject

class LocationPagingDataSource @Inject constructor(
    private val api: RickAndMortyApi
) : PagingSource<Int, Locations>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Locations> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getLocations(page)
            val pageCount = response.info?.pages ?: 0
            LoadResult.Page(
                data = response.results.toResults(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty() || page == pageCount) null else page.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Locations>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}