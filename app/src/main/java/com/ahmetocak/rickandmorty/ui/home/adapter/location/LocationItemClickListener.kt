package com.ahmetocak.rickandmorty.ui.home.adapter.location

import com.ahmetocak.rickandmorty.domain.model.location.Locations

interface LocationItemClickListener {
    fun onLocationItemClick(locations: Locations)
}