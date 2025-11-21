package com.example.lyricsgame.data.model

import com.example.lyricsgame.domain.viewentity.GenreViewEntity

data class Genre(
    val id: Int,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val type: String
) {
    fun toViewEntity(): GenreViewEntity {
        return GenreViewEntity(id = this.id, name = this.name, picture = this.picture)
    }
}