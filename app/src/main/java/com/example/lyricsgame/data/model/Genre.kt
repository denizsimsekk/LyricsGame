package com.example.lyricsgame.data.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String,
    @DrawableRes val cover: Int,
    val description: String
)
