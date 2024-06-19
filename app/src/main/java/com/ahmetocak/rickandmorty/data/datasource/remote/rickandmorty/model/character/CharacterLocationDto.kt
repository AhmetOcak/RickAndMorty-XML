package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character

import com.google.gson.annotations.SerializedName

data class CharacterLocationDto(
    @SerializedName("name")
    val locationName: String? = null,

    @SerializedName("url")
    val url: String? = null
)
