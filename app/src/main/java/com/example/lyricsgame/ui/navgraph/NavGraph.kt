package com.example.lyricsgame.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lyricsgame.ui.game.GameScreen
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
        composable<Route.GameScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.GameScreen>()
            GameScreen(
                genreId = args.genreId
            )
        }
        composable<Route.GenreListScreen> { backStackEntry ->
            GenreListScreen(navController = navController)
        }

    }
}