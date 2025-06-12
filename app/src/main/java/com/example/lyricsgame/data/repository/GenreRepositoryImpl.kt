package com.example.lyricsgame.data.repository

import android.content.Context
import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.repository.IGenreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : IGenreRepository {
    override fun getGenreList(): Flow<List<Genre>> = flow {
        emit(
            listOf(
                Genre("Pop", context.getDrawable(R.drawable.ic_pop_cover)),
                Genre("Rock", context.getDrawable(R.drawable.ic_rock_cover)),
                Genre("Rap", context.getDrawable(R.drawable.ic_rap_cover)),
                Genre("Country", context.getDrawable(R.drawable.ic_country_cover)),
                Genre("Jazz", context.getDrawable(R.drawable.ic_jazz_cover)),
                Genre("Metal", context.getDrawable(R.drawable.ic_metal_cover))
            )
        )
    }
}