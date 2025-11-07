package com.example.lyricsgame.domain.usecase.globalchart

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlobalChartUseCase @Inject constructor(private val globalChartRepository: IGlobalChartRepository) {

    operator fun invoke(): Flow<Resource<GlobalChart>> {
        return globalChartRepository.getGlobalChartTrackList()

    }

}