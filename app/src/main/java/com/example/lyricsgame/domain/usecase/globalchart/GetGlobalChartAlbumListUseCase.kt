package com.example.lyricsgame.domain.usecase.globalchart

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.viewentity.AlbumViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlobalChartAlbumListUseCase @Inject constructor(private val getGlobalChartRepository: IGlobalChartRepository) {

    operator fun invoke(): Flow<Resource<List<AlbumViewEntity>?>> {
        return getGlobalChartRepository.getGlobalChartAlbumList()
    }

}