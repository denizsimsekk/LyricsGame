package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Artist
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(private val api: Api) : IArtistRepository {

    override fun getArtistList(): List<Artist> {
        TODO("Not yet implemented")
    }

    override fun getArtistListByGenre(genreId: Int): List<Artist> {
        TODO("Not yet implemented")
    }

}