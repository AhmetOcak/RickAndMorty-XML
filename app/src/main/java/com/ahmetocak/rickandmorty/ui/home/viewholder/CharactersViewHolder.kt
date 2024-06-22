package com.ahmetocak.rickandmorty.ui.home.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.ahmetocak.rickandmorty.databinding.ItemCharacterBinding
import com.ahmetocak.rickandmorty.domain.model.character.Character

class CharactersViewHolder(
    val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        characterItem: Character,
        genderImg: Drawable
    ) {
        binding.apply {
            this.characterItem = characterItem
            this.genderImg = genderImg
            executePendingBindings()
        }
    }
}