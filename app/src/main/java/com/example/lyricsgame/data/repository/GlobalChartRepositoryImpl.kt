package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GlobalChartRepositoryImpl @Inject constructor(private val api: Api) : IGlobalChartRepository {

    override fun getGlobalChartTrackList(): Flow<Resource<GlobalChart>> {
        return flow {
            val response = api.getGlobalChart()
            if (response.isSuccess == true) {
                response.data?.let { emit(Resource.Success(it)) }
            } else {
                emit(Resource.Failure("Error Occurred! Please Try Again"))
            }
        }
    }
}