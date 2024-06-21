package com.ahmetocak.rickandmorty.ui.home.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.ahmetocak.rickandmorty.databinding.ItemLocationBinding
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import com.google.android.material.card.MaterialCardView

class LocationsViewHolder(
    val binding: ItemLocationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(locationItem: Locations, locationImage: Drawable) {

        binding.apply {
            this.locationItem = locationItem
            this.locationImage = locationImage
            executePendingBindings()
        }
    }
}