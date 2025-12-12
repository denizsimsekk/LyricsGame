package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Artist
import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IArtistRepository
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(private val api: Api) : IArtistRepository, BaseRepository() {

    override fun getArtistTrackList(artistId: Int): Flow<Resource<List<TrackViewEntity>?>> {
        return safeApiCall(apiCall = { api.getArtistTrackList(artistId = artistId) }, extractData = { res ->
            res.data?.map { it.toViewEntity() }

        })
    }

}