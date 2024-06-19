package com.ahmetocak.rickandmorty.domain.model.location

data class Locations(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String> = listOf(),
    val url: String,
    val createdDate: String
)