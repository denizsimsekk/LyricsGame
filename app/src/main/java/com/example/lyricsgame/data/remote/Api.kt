package com.example.lyricsgame.data.remote

import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.ResponseDto
import retrofit2.http.GET

interface Api {

    @GET("genre")
    suspend fun getGenreList(): ResponseDto<List<Genre>>

}