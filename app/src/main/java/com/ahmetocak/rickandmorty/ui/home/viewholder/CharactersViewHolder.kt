package com.ahmetocak.rickandmorty.ui.home.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.ahmetocak.rickandmorty.databinding.ItemCharacterBinding
import com.google.android.material.card.MaterialCardView

class CharactersViewHolder(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val characterCard: MaterialCardView = binding.mcvCharacterItem

    fun bind(
        characterImg: String,
        characterName: String,
        genderImg: Drawable
    ) {
        characterCard.setOnClickListener {
            // TODO: Navigate Character Screen
        }

        binding.apply {
            this.characterName = characterName
            this.characterImg = characterImg
            this.genderImg = genderImg
            executePendingBindings()
        }
    }
}