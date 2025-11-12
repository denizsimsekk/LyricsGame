package com.example.lyricsgame.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lyricsgame.ui.theme.colorCharcoal

@Composable
fun OptionItem(option: String, isCorrectAnswer: Boolean?, selectedOption: String?, selectOption: (String) -> Unit) {
    val animatedAlpha: Float by animateFloatAsState(
        if ((isCorrectAnswer == true && selectedOption == option) || (isCorrectAnswer == false && selectedOption == option)) 1.2f else 1f,
        label = "alpha"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectOption.invoke(option)
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = option, modifier = Modifier
                .scale(animatedAlpha)
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .fillMaxWidth()
                .background(color = if (isCorrectAnswer == true && selectedOption == option) Color.Green else if (isCorrectAnswer == false && selectedOption == option) Color.Red else colorCharcoal)
                .padding(vertical = 4.dp), textAlign = TextAlign.Center, color = Color.White
        )
    }
}