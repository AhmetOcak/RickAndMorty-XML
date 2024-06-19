package com.ahmetocak.rickandmorty.data.datasource.remote.rickandmorty.model.location

import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("pages")
    val pages: Int?,

    @SerializedName("next")
    val nextPageUrl: String?,

    @SerializedName("prev")
    val prevPageUrl: String?
)
