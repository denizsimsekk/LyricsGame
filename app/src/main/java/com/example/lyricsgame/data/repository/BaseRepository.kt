package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.data.model.ResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepository {

    fun <T, R> safeApiCall(
        apiCall: suspend () -> ResponseDto<T?>,
        extractData: (ResponseDto<T?>) -> R
    ): Flow<Resource<R>> = flow {
        try {
            val apiResponse = apiCall()
            if (apiResponse.data != null) {
                emit(Resource.Success(extractData(apiResponse)))
            } else {
                emit(Resource.Failure("Error!"))
            }
        } catch (e: Exception) {
            emit(Resource.Failure(parseError(e)))
        }
    }.flowOn(Dispatchers.IO)

    fun parseError(e: Exception): String {
        return ""
    }

}