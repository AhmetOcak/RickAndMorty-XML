package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.character

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("species")
    val species: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("origin")
    val origin: OriginDto? = null,

    @SerializedName("location")
    val location: CharacterLocationDto? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("episode")
    val episode: List<String> = listOf(),

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("created")
    val created: String? = null
)
