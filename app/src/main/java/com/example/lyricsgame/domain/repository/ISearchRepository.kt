package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

    fun searchArtist(query: String): Flow<Resource<List<ArtistViewEntity>?>>

}