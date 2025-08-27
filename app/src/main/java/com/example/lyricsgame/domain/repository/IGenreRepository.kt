package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenreList():  Flow<Resource<List<Genre>>>

}