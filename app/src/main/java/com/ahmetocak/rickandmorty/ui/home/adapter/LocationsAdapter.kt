package com.ahmetocak.rickandmorty.ui.home.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ahmetocak.rickandmorty.databinding.ItemLocationBinding
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import com.ahmetocak.rickandmorty.ui.home.viewholder.LocationsViewHolder

class LocationsAdapter(
    private val onSetLocationImages: (String) -> Drawable,
    private val locationItemClickListener: LocationItemClickListener,
    private val initializeCharacterList: (List<String>) -> Unit
) : PagingDataAdapter<Locations, LocationsViewHolder>(LocationsDiffUtilCallback()), LocationItemClickListener {

    companion object {
        private var selectedLocationId = 1
        private var isCharacterListInitialized = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder =
        LocationsViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(
                locationItem = item,
                locationImage = if (item.id != selectedLocationId) {
                    onSetLocationImages(item.name).apply {
                        colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
                    }
                } else onSetLocationImages(item.name).apply { colorFilter = null }
            )
            holder.binding.locationItemClickListener = this

            if (position == 0 && !isCharacterListInitialized) {
                initializeCharacterList(item.residents)
                isCharacterListInitialized = true
            }
        }
    }

    override fun onLocationItemClick(locations: Locations) {
        selectedLocationId = locations.id
        notifyDataSetChanged()
        locationItemClickListener.onLocationItemClick(locations)
    }

    class LocationsDiffUtilCallback : DiffUtil.ItemCallback<Locations>() {
        override fun areItemsTheSame(oldItem: Locations, newItem: Locations): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Locations, newItem: Locations): Boolean {
            return oldItem == newItem
        }
    }
}