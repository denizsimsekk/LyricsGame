package com.example.lyricsgame.domain.usecase.track

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.domain.repository.ITrackRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackDetailUseCase @Inject constructor(private val trackRepository: ITrackRepository) {

    operator fun invoke(id: Double): Flow<Resource<Track>> {
        return trackRepository.getTrackDetail(id = id)
    }

}