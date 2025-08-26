package com.example.lyricsgame.data.model

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val errorMessage: String) : Resource<Nothing>()
}