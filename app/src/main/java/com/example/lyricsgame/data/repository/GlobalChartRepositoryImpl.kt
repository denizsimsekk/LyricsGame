package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.viewentity.AlbumViewEntity
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GlobalChartRepositoryImpl @Inject constructor(private val api: Api) : IGlobalChartRepository {

    override suspend fun getGlobalChart(): GlobalChart {
        val response = api.getGlobalChart()
        return response
    }

    override fun getGlobalChartArtistList(): Flow<Resource<List<ArtistViewEntity>>> {
        return flow {
            try {
                val response = getGlobalChart()
                emit(Resource.Success(response.artists.data.map { it.toViewEntity() }))
            } catch (e: Exception) {
                emit(Resource.Failure(e.localizedMessage ?: ""))
            }
        }
    }

    override fun getGlobalChartAlbumList(): Flow<Resource<List<AlbumViewEntity>>> {
        return flow {
            try {
                val response = getGlobalChart()
                emit(Resource.Success(response.albums.data.map { it.toViewEntity() }))
            } catch (e: Exception) {
                emit(Resource.Failure(e.localizedMessage ?: ""))
            }
        }
    }
}