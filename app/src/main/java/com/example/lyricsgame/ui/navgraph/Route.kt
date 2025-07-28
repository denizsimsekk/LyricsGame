package com.example.lyricsgame.ui.navgraph

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object HomeScreen : Route()

    @Serializable
    data class GameScreen(val genreId: Int) : Route()

    @Serializable
    object GenreListScreen : Route()
}