package com.example.lyricsgame.domain.usecase.track

import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopTrackListByGenreUseCase @Inject constructor(private val genreRepository: IGenreRepository) {

    operator fun invoke(genreId: Int): Flow<Resource<List<TrackViewEntity>?>> {
        return genreRepository.getTopTrackListByGenre(genreId = genreId)
    }

}