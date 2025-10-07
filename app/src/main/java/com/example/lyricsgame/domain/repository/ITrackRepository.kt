package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.Track
import kotlinx.coroutines.flow.Flow

interface ITrackRepository {

    fun getTrackDetail(id: Double): Flow<Resource<Track>>

}