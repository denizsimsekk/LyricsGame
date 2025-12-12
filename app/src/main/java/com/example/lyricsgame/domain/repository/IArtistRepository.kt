package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow

interface IArtistRepository {

    fun getArtistTrackList(artistId: Int): Flow<Resource<List<TrackViewEntity>?>>

}