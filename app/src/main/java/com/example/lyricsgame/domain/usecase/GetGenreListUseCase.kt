package com.example.lyricsgame.domain.usecase

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.repository.IGenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(private val genreRepository: IGenreRepository) {

    operator fun invoke(): Flow<List<Genre>> {
        return genreRepository.getGenreList()
    }
}