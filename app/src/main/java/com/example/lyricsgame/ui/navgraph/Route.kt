package com.example.lyricsgame.ui.navgraph

import com.example.lyricsgame.domain.viewentity.GameType
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object HomeScreen : Route()

    @Serializable
    data class GuessTrackScreen(val type: GameType, val genreId: Int? = null, val genreName: String? = null, val artistId: Int? = null) : Route()

    @Serializable
    object GenreListScreen : Route()

    @Serializable
    object SearchScreen : Route()

    @Serializable
    object GuessArtistScreen : Route()

    @Serializable
    object GuessAlbumScreen : Route()
}