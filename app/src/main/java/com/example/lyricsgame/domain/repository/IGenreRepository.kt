package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenreList(): List<Genre>

}