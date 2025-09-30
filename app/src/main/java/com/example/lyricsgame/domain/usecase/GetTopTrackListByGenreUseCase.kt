package com.example.lyricsgame.domain.usecase

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.domain.repository.IGenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopTrackListByGenreUseCase @Inject constructor(private val genreRepository: IGenreRepository) {

    operator fun invoke(genreId: Int): Flow<Resource<List<Track>>> {
        return genreRepository.getTopTrackListByGenre(genreId = genreId)
    }

}