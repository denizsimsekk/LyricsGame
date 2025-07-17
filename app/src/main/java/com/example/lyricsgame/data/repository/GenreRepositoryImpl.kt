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
                Genre(id = 0, name = "Pop", cover = R.drawable.ic_pop_cover),
                Genre(id = 1, name = "Rock", cover = R.drawable.ic_rock_cover),
                Genre(id = 2, name = "Rap", cover = R.drawable.ic_rap_cover),
                Genre(id = 3, name = "Country", cover = R.drawable.ic_country_cover),
                Genre(id = 4, name = "Jazz", cover = R.drawable.ic_jazz_cover),
                Genre(id = 5, name = "Metal", cover = R.drawable.ic_metal_cover)
            )
        )
    }
}