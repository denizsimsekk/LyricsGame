package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GlobalChartRepositoryImpl @Inject constructor(private val api: Api) : IGlobalChartRepository {

    override fun getGlobalChartTrackList(): Flow<Resource<GlobalChart>> {
        return flow {
            try {
                val response = api.getGlobalChart()
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Failure(e.localizedMessage ?: ""))
            }
        }
    }
}