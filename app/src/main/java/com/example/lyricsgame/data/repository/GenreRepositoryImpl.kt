package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.viewentity.GenreViewEntity
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    val api: Api
) : IGenreRepository {

    override fun getGenreList(): Flow<Resource<List<GenreViewEntity>>> {
        return flow {
            val response = api.getGenreList()
            if (response.data.isNullOrEmpty().not()) {
                response.data?.let { emit(Resource.Success(it.map { it.toViewEntity() })) }
            } else {
                emit(Resource.Failure("Error Occurred! Please Try Again"))
            }
        }
    }

    override fun getTopTrackListByGenre(genreId: Int): Flow<Resource<List<TrackViewEntity>>> {
        return flow {
            try {
                val response = api.getTopTrackListByGenre(genreId)
                if (!response.data.isNullOrEmpty()) {
                    emit(Resource.Success(response.data.map { it.toViewEntity() }))
                } else {
                    emit(Resource.Failure("Error Occurred! Please Try Again"))
                }

            } catch (e: Exception) {
                emit(Resource.Failure(e.message ?: "Unexpected error occurred"))
            }
        }
    }


}