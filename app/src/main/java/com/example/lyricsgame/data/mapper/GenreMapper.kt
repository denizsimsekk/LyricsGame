package com.example.lyricsgame.data.mapper

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.model.GenreViewEntity

fun Genre.toViewEntity(): GenreViewEntity {
    return GenreViewEntity(id = this.id, name = this.name, picture = this.picture, type = this.type)
}

fun List<Genre>.toViewEntityList(): List<GenreViewEntity> {
    return orEmpty().map { it.toViewEntity() }.toList()
}