package com.example.lyricsgame.data.model

import com.example.lyricsgame.domain.viewentity.AlbumViewEntity

data class Album(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val id: Int,
    val link: String,
    val md5_image: String,
    val position: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
) {
    fun toViewEntity(): AlbumViewEntity {
        return AlbumViewEntity(artist = this.artist.toViewEntity(), coverMedium = this.cover_medium, id = this.id, title = this.title, trackList = this.tracklist)
    }
}