package com.example.lyricsgame.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.lyricsgame.ui.theme.robotoFamily

@Composable
fun AppText(
    text: String, modifier: Modifier = Modifier,
    size: TextUnit = 16.sp,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = robotoFamily,
        fontWeight = fontWeight,
        fontSize = size,
        color = color,
        textAlign = textAlign
    )
}