package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.viewentity.GenreViewEntity
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    val api: Api
) : IGenreRepository, BaseRepository() {

    override fun getGenreList(): Flow<Resource<List<GenreViewEntity>?>> {
        return safeApiCall(apiCall = { api.getGenreList() }, extractData = { res ->
            res.data?.map { genre ->
                genre.toViewEntity()
            }
        })
    }

    override fun getTopTrackListByGenre(genreId: Int): Flow<Resource<List<TrackViewEntity>?>> {
        return safeApiCall(apiCall = { api.getTopTrackListByGenre(genreId = genreId) }, extractData = { res ->
            res.data?.map { genre ->
                genre.toViewEntity()
            }
        })
    }


}