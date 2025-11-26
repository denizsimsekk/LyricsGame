package com.example.lyricsgame.domain.usecase.globalchart

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlobalChartArtistsUseCase @Inject constructor(private val globalChartRepository: IGlobalChartRepository) {

    operator fun invoke(): Flow<Resource<List<ArtistViewEntity>?>> {
        return globalChartRepository.getGlobalChartArtistList()
    }

}