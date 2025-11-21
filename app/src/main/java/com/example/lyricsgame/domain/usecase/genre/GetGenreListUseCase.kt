package com.example.lyricsgame.domain.usecase.genre

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.viewentity.GenreViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(private val genreRepository: IGenreRepository) {

    operator fun invoke() : Flow<Resource<List<GenreViewEntity>>>  {
        return genreRepository.getGenreList()
    }
}