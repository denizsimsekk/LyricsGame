package com.example.lyricsgame.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lyricsgame.ui.game.GameScreen
import com.example.lyricsgame.ui.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Route.HomeGraph.route
) {
    NavHost(navController, startDestination)
    {
        navigation(startDestination = Route.HomeScreen.route, route = Route.HomeGraph.route) {
            composable(Route.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Route.GameScreen.route) {
                GameScreen()
            }
        }
    }
}