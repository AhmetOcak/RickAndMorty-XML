package com.ahmetocak.rickandmorty.data.utils

import com.ahmetocak.rickandmorty.common.Response

fun <I: Any, O : Any> Response<I>.mapResponse(mapper: (I) -> O): Response<O> {
    return when (this) {
        is Response.Success -> {
            Response.Success(mapper.invoke(this.data))
        }
        is Response.Error -> {
            Response.Error(message)
        }
    }
}