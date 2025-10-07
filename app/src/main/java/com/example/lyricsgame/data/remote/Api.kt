package com.example.lyricsgame.data.remote

import com.example.lyricsgame.data.model.Artist
import com.example.lyricsgame.data.model.ChartItem
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.ResponseDto
import com.example.lyricsgame.data.model.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("genre")
    suspend fun getGenreList(): ResponseDto<List<Genre>>

    @GET("chart")
    suspend fun getGlobalChart(): ResponseDto<List<ChartItem>>

    @GET("genre/{genreId}/artists")
    suspend fun getArtistListByGenre(
        @Path("genreId") genreId: Int,
    ): ResponseDto<List<Artist>>

    @GET("chart/{genreId}/tracks")
    suspend fun getTopTrackListByGenre(
        @Path("genreId") genreId: Int,
    ): ResponseDto<List<Track>>

    @GET("track/{id}")
    suspend fun getTrack(@Path("id") id: Double): Track

}