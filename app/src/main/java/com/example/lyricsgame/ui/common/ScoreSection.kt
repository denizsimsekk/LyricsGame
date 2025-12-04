package com.example.lyricsgame.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.lyricsgame.R

@Composable
fun ScoreSection(correctAnswerCount: Int, questionCount: Int) {
    val context = LocalContext.current
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AppText(
            text = "\uD83D\uDCAB${correctAnswerCount}/${questionCount}\uD83D\uDCAB",
            fontWeight = FontWeight.Bold,
            size = 36.sp
        )
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (android.os.Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        AsyncImage(
            model = R.drawable.score,
            contentDescription = null,
            imageLoader = imageLoader,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(600.dp)
                .height(300.dp)
        )
    }
}