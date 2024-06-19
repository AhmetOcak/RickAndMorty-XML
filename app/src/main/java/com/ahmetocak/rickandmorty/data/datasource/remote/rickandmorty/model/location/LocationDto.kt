package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.location

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("info")
    val info: InfoDto?,

    @SerializedName("results")
    val results: List<ResultsDto> = listOf()
)