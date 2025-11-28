package com.example.lyricsgame.ui.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lyricsgame.ui.base.BaseScreen

@Composable
fun SearchScreen(navController: NavController) {
    BaseScreen(isTopBarShown = true, topBarTitle = "Search Your Favourite Artist", navController = navController) {

    }
}

@Composable
private fun MainContent() {

}