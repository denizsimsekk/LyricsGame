package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Artist

interface IArtistRepository {

    fun getArtistList(): List<Artist>
    fun getArtistListByGenre(genreId: Int): List<Artist>

}