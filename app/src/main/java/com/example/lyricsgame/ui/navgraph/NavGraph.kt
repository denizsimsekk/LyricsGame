package com.example.lyricsgame.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lyricsgame.ui.SearchScreen
import com.example.lyricsgame.ui.game.GuessTrackScreen
import com.example.lyricsgame.ui.genre.GenreListScreen
import com.example.lyricsgame.ui.home.HomeScreen


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
                genreId = args.genreId,
                genreName = args.genreName,
                navController = navController
            )
        }
        composable<Route.GenreListScreen> { backStackEntry ->
            GenreListScreen(navController = navController)
        }
        composable<Route.SearchScreen> { backStackEntry ->
            SearchScreen(navController = navController)
        }
    }
}