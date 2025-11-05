package com.example.lyricsgame.domain.usecase.genre

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IGenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(private val genreRepository: IGenreRepository) {

    operator fun invoke():  Flow<Resource<List<Genre>>>  {
        return genreRepository.getGenreList()
    }
}