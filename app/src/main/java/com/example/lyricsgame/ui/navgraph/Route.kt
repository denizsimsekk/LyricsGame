package com.example.lyricsgame.ui.navgraph

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object HomeScreen : Route()

    @Serializable
    data class GuessTrackScreen(val genreId: Int, val genreName: String) : Route()

    @Serializable
    object GenreListScreen : Route()

    @Serializable
    object SearchScreen : Route()

    @Serializable
    object GuessArtistScreen : Route()

}