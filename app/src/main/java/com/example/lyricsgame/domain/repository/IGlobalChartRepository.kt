package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.viewentity.AlbumViewEntity
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow

interface IGlobalChartRepository {

    fun getGlobalChartArtistList(): Flow<Resource<List<ArtistViewEntity>?>>

    fun getGlobalChartAlbumList(): Flow<Resource<List<AlbumViewEntity>?>>

    fun getGlobalChartTrackList(): Flow<Resource<List<TrackViewEntity>?>>

}