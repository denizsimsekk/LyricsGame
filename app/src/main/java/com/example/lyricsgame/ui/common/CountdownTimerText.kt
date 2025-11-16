package com.example.lyricsgame.ui.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CountdownTimerText(remainingTime: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(targetState = remainingTime.toString(), label = "CountDownTimerText") {
            AppText(
                text = if (remainingTime > 0) it else "Go!",
                size = 48.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}