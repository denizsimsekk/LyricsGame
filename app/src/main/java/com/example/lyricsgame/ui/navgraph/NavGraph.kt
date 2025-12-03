package com.example.lyricsgame.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lyricsgame.ui.album.GuessAlbumScreen
import com.example.lyricsgame.ui.artist.GuessArtistScreen
import com.example.lyricsgame.ui.track.GuessTrackScreen
import com.example.lyricsgame.ui.genre.GenreListScreen
import com.example.lyricsgame.ui.home.HomeScreen
import com.example.lyricsgame.ui.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController, Route.HomeScreen)
    {
        composable<Route.HomeScreen> {
            HomeScreen(navController = navController)
        }
        composable<Route.GuessTrackScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.GuessTrackScreen>()
            GuessTrackScreen(
                type = args.type,
                genreId = args.genreId,
                genreName = args.genreName,
                artistId = args.artistId,
                navController = navController
            )
        }
        composable<Route.GenreListScreen> {
            GenreListScreen(navController = navController)
        }
        composable<Route.SearchScreen> {
            SearchScreen(navController = navController)
        }
        composable<Route.GuessArtistScreen> {
            GuessArtistScreen(navController = navController)
        }
        composable<Route.GuessAlbumScreen> {
            GuessAlbumScreen(navController = navController)
        }
    }
}