package com.example.lyricsgame.domain.viewentity

data class AlbumViewEntity(
    val artist: ArtistViewEntity,
    val coverMedium: String,
    val id: Int,
    val title: String,
    val trackList: String,
)