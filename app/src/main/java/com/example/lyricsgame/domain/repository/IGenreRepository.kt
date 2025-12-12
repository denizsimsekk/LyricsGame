package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.domain.viewentity.GenreViewEntity
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenreList(): Flow<Resource<List<GenreViewEntity>?>>

    fun getTopTrackListByGenre(genreId: Int): Flow<Resource<List<TrackViewEntity>?>>

}