package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.ITrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(private val api: Api) : ITrackRepository {
    override fun getTrackDetail(id: Double): Flow<Resource<Track>> {
        return flow {
            val response = api.getTrack(id = id)
            emit(Resource.Success(response))
        }
    }
}