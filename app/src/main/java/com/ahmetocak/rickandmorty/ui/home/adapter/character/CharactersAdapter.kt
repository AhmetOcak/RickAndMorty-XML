package com.ahmetocak.rickandmorty.ui.home.adapter.character

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ahmetocak.rickandmorty.databinding.ItemCharacterBinding
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.ui.home.viewholder.CharactersViewHolder

class CharactersAdapter(
    private val setCharacterGender: (String) -> Drawable,
    private val characterItemClickListener: CharacterItemClickListener
) : ListAdapter<Character, CharactersViewHolder>(CharactersDiffUtilCallback()),
    CharacterItemClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            characterItem = item,
            genderImg = setCharacterGender(item.gender)
        )
        holder.binding.characterItemClickListener = this
    }

    override fun onCharacterItemClick(item: Character) {
        characterItemClickListener.onCharacterItemClick(item)
    }

    class CharactersDiffUtilCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}