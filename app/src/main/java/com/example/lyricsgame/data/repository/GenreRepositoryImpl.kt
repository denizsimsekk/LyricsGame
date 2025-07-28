package com.example.lyricsgame.data.repository

import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.repository.IGenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
) : IGenreRepository {

    override fun getGenreList(): List<Genre> =
        listOf(
            Genre(id = 0, name = "Pop", cover = R.drawable.ic_pop_cover, description = "Chart-toppers: Taylor Swift, The Weeknd, Dua Lipa,"),
            Genre(id = 1, name = "Rock", cover = R.drawable.ic_rock_cover, description = "Most streamed: Foo Fighters, Imagine Dragons, Greta Van Fleet"),
            Genre(id = 2, name = "Rap", cover = R.drawable.ic_rap_cover, description = "Top hits by: Drake, Kendrick Lamar, Cardi B"),
            Genre(id = 3, name = "Country", cover = R.drawable.ic_country_cover, description = "Feel the rhythm: Luke Combs, Carrie UnderWood, Chris Stapleton"),
            Genre(id = 4, name = "Jazz", cover = R.drawable.ic_jazz_cover, description = "Catchy tunes: Miles Davis, John Coltrane, Esperanza Spalding"),
            Genre(id = 5, name = "Metal", cover = R.drawable.ic_metal_cover, description = "Radio staples: Metallica, Iron Maiden, Slipknot")
        )


}