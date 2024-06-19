package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character

import com.google.gson.annotations.SerializedName

data class OriginDto(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("url")
    val url: String? = null
)
