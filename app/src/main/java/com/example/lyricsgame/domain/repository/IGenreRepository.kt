package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.Track
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenreList(): Flow<Resource<List<Genre>>>

    fun getTopTrackListByGenre(genreId: Int): Flow<Resource<List<Track>>>

}