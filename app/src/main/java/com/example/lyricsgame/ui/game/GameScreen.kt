package com.example.lyricsgame.ui.game

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lyricsgame.ui.common.AppText

@Composable
fun GameScreen(genreId: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent(viewModel = viewModel())
    }
}

@Composable
private fun MainContent(viewModel: GameViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.updateRemainingTime()
    }
    CountdownTimer(uiState = uiState)
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