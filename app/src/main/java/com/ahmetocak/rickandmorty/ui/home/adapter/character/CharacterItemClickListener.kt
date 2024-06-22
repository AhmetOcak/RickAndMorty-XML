package com.ahmetocak.rickandmorty.ui.home.adapter.character

import com.ahmetocak.rickandmorty.domain.model.character.Character

interface CharacterItemClickListener {
    fun onCharacterItemClick(item: Character)
}