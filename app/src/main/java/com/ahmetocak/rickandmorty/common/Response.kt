package com.ahmetocak.rickandmorty.common

/**
 * Base API response type.
 * @param T type of Response
 */
sealed interface Response<out T> {
    class Success<T>(val data: T) : Response<T>
    class Error<T>(val message: String) : Response<T>
}