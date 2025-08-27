package com.example.lyricsgame.data.repository

import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.domain.repository.IGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    val api: Api
) : IGenreRepository {

    override fun getGenreList(): Flow<Resource<List<Genre>>> {
        return flow {
            val response = api.getGenreList()
            if (response.data.isNullOrEmpty().not()) {
                response.data?.let { emit(Resource.Success(it)) }
            } else {
                emit(Resource.Failure("Error Occurred! Please Try Again"))
            }
        }
    }


}