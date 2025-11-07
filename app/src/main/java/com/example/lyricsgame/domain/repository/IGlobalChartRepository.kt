package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface IGlobalChartRepository {

    fun getGlobalChartTrackList(): Flow<Resource<GlobalChart>>

}