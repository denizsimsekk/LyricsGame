package com.example.lyricsgame.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lyricsgame.ui.common.AppText
import kotlinx.coroutines.delay

@Composable
fun GameScreen(genreId: Int, modifier: Modifier = Modifier) {
    var remainingTime by remember {
        mutableIntStateOf(3)
    }
    Column(modifier = modifier.fillMaxSize()) {
        MainContent()
    }
}

@Composable
private fun MainContent() {
    CountdownTimer()
}

@Composable
fun CountdownTimer() {
    val timeLeft = remember { mutableStateOf(3) }

    LaunchedEffect(Unit) {
        while (timeLeft.value > 0) {
            delay(1000L)
            timeLeft.value -= 1
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = if (timeLeft.value > 0) timeLeft.value.toString() else "Go!",
            size = 48.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}