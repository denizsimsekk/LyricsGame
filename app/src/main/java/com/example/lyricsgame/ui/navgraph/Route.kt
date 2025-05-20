package com.example.lyricsgame.ui.navgraph

enum class Screen {
    HOME,
    GAME_SCREEN
}

sealed class Route(val route:String){
    object HomeScreen : Route(Screen.HOME.name)
    object GameScreen : Route(Screen.GAME_SCREEN.name)
    object HomeGraph : Route("home_graph")//TODO move to a constant or enum
}