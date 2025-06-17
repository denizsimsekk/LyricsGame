package com.example.lyricsgame.data.repository

import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.repository.IGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
) : IGenreRepository {
    override fun getGenreList(): Flow<List<Genre>> = flow {
        emit(
            listOf(
                Genre("Pop", R.drawable.ic_pop_cover),
                Genre("Rock", R.drawable.ic_rock_cover),
                Genre("Rap", R.drawable.ic_rap_cover),
                Genre("Country", R.drawable.ic_country_cover),
                Genre("Jazz", R.drawable.ic_jazz_cover),
                Genre("Metal", R.drawable.ic_metal_cover)
            )
        )
    }
}