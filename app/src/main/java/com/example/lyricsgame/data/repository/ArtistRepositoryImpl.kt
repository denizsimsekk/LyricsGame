package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Artist
import com.example.lyricsgame.domain.repository.IArtistRepository
import kotlinx.coroutines.flow.Flow

class ArtistRepositoryImpl : IArtistRepository {
    override fun getArtistList(): List<Artist> {
        TODO("Not yet implemented")
    }

    override fun getArtistListByGenre(genreId: Int): List<Artist> {
        TODO("Not yet implemented")
    }

}