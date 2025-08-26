package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.model.GenreViewEntity
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenreList():  Flow<Resource<List<GenreViewEntity>>>

}