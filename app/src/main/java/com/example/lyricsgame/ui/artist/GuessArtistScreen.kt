package com.example.lyricsgame.ui.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.lyricsgame.ui.common.AppTopBar
import com.example.lyricsgame.ui.common.CountdownTimerText

@Composable
fun GuessArtistScreen(viewModel: GuessArtistViewModel = hiltViewModel(), navController: NavController) {
    MainContent(viewModel = viewModel, navController = navController)
}

@Composable
private fun MainContent(viewModel: GuessArtistViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.updateRemainingTime()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        AppTopBar(
            title = "Guess the Artist"
        ) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            when {
                uiState.remainingTimeToStartGame > 0 -> {
                    CountdownTimerText(remainingTime = uiState.remainingTimeToStartGame)
                }
            }
        }
    }
}