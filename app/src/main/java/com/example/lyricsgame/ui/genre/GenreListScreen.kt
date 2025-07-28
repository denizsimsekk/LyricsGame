package com.example.lyricsgame.ui.genre

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lyricsgame.ui.common.AppText

@Composable
fun GenreListScreen(modifier: Modifier = Modifier) {

    AppText(
        "Choose a genre to start playing.",
        modifier = modifier.padding(start = 16.dp, top = 16.dp),
        fontWeight = FontWeight.Bold,
        size = 18.sp
    )


}