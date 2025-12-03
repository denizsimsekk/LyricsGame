package com.example.lyricsgame.data.remote

import com.example.lyricsgame.data.model.Artist
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.data.model.GlobalChart
import com.example.lyricsgame.data.model.ResponseDto
import com.example.lyricsgame.data.model.Track
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("genre")
    suspend fun getGenreList(): ResponseDto<List<Genre>?>

    @GET("chart")
    suspend fun getGlobalChart(): GlobalChart

    @GET("chart/{genreId}/tracks")
    suspend fun getTopTrackListByGenre(
        @Path("genreId") genreId: Int,
    ): ResponseDto<List<Track>?>

    @GET("search/artist")
    suspend fun searchArtist(@Query("q") query: String): ResponseDto<List<Artist>?>

    @GET("artist/{artistId}/top?limit=20")
    suspend fun getArtistTrackList(@Path("artistId") artistId: Int): ResponseDto<List<Track>?>

}