package com.example.lyricsgame.data.model

import com.example.lyricsgame.domain.viewentity.ArtistViewEntity

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String? = null,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
) {
    fun toViewEntity(): ArtistViewEntity {
        return ArtistViewEntity(id = this.id, name = this.name, picture = this.picture ?: this.picture_medium)
    }

}