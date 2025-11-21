package com.example.lyricsgame.data.model

import com.example.lyricsgame.domain.viewentity.TrackViewEntity

data class Track(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Double,
    val link: String,
    val md5_image: String,
    val position: Int,
    val preview: String,
    val rank: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
) {
    fun toViewEntity(): TrackViewEntity {
        return TrackViewEntity(artist = this.artist.toViewEntity(), image = "https://e-cdns-images.dzcdn.net/images/cover/${this.md5_image}/500x500.jpg", preview = this.preview, title = this.title)
    }
}