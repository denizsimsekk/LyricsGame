package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.data.model.ResponseDto
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.viewentity.AlbumViewEntity
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GlobalChartRepositoryImpl @Inject constructor(private val api: Api) : IGlobalChartRepository, BaseRepository() {

    private suspend fun fetchGlobalChart(): ResponseDto<GlobalChart?> {
        val res = api.getGlobalChart()
        return ResponseDto(
            data = GlobalChart(
                tracks = res.tracks,
                artists = res.artists,
                albums = res.albums,
                playlists = res.playlists,
                podcasts = res.podcasts
            )
        )
    }

    override fun getGlobalChartArtistList(): Flow<Resource<List<ArtistViewEntity>?>> {
        return safeApiCall(apiCall = { fetchGlobalChart() }, extractData = { res -> res.data?.artists?.data?.map { it.toViewEntity() } })
    }

    override fun getGlobalChartAlbumList(): Flow<Resource<List<AlbumViewEntity>?>> {
        return safeApiCall(apiCall = { fetchGlobalChart() }, extractData = { res -> res.data?.albums?.data?.map { it.toViewEntity() } })
    }

    override fun getGlobalChartTrackList(): Flow<Resource<List<TrackViewEntity>?>> {
        return safeApiCall(apiCall = { fetchGlobalChart() }, extractData = { res -> res.data?.tracks?.data?.map { it.toViewEntity() } })
    }
}