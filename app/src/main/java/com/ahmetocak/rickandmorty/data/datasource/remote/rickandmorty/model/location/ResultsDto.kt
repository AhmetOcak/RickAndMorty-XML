package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.location

import com.google.gson.annotations.SerializedName

data class ResultsDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("dimension")
    val dimension: String?,

    @SerializedName("residents")
    val residents: List<String> = listOf(),

    @SerializedName("url")
    val url: String?,

    @SerializedName("created")
    val createdDate: String?
)
