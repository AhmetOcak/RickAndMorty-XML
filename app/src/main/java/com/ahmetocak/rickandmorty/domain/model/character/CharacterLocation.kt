package com.ahmetocak.rickandmorty.domain.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterLocation(
    val locationName: String,
    val url: String
): Parcelable
