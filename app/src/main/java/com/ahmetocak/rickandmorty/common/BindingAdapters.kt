package com.ahmetocak.rickandmorty.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun loadNetworkImage(view: ImageView, url: String) {
    view.load(url) {
        crossfade(true)
    }
}