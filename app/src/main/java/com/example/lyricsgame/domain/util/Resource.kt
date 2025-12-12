package com.example.lyricsgame.domain.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val errorMessage: String) : Resource<Nothing>()
}