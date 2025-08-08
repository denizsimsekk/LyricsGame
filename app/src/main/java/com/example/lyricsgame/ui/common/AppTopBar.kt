package com.example.lyricsgame.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lyricsgame.R
import com.example.lyricsgame.ui.theme.charcoal

@Composable
fun AppTopBar(modifier: Modifier = Modifier, title: String, onBackButtonClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(charcoal)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = modifier.clickable { onBackButtonClick.invoke() },
            colorFilter = ColorFilter.tint(Color.White)
        )
        AppText(
            text = title,
            fontWeight = FontWeight.SemiBold,
            size = 18.sp,
            modifier = modifier.padding(horizontal = 8.dp),
            color = Color.White
        )
    }
}