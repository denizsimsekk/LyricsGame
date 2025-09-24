package com.example.lyricsgame.ui.game

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.common.AppTopBar

@Composable
fun GameScreen(genreId: Int, genreName: String, navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent(genreId = genreId, genreName = genreName, navController = navController, viewModel = viewModel())
    }
}

@Composable
private fun MainContent(genreId: Int, genreName: String, navController: NavController, viewModel: GameViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getGenreSongList()
    }
    if (uiState.remainingTimeToStartGame > 0) {
        CountdownTimer(uiState = uiState)
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(
                title = genreName
            ) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun CountdownTimer(uiState: GameUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(targetState = uiState.remainingTimeToStartGame.toString(), label = "CountDownTimerText") {
            AppText(
                text = if (uiState.remainingTimeToStartGame > 0) it else "Go!",
                size = 48.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}