package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.ISearchRepository
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: Api) : ISearchRepository, BaseRepository() {

    override fun searchArtist(query: String): Flow<Resource<List<ArtistViewEntity>?>> {
        return safeApiCall(apiCall = { api.searchArtist(query = query) }, extractData = { it.data?.map { it.toViewEntity() } })
    }

}