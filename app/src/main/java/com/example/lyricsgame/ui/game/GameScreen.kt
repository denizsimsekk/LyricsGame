package com.example.lyricsgame.ui.game

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.common.AppTopBar
import com.example.lyricsgame.ui.theme.charcoal

@Composable
fun GameScreen(genreId: Int, genreName: String, navController: NavController, viewModel: GameViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent(genreId = genreId, genreName = genreName, navController = navController, viewModel = viewModel)
    }
}

@Composable
private fun MainContent(genreId: Int, genreName: String, navController: NavController, viewModel: GameViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGenreSongList(genreId = genreId)
    }

    LaunchedEffect(uiState.remainingTimeToStartGame) {
        if (uiState.remainingTimeToStartGame <= 0) {
            viewModel.getAiResponse()
        }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = genreName
        ) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            if (uiState.remainingTimeToStartGame > 0) {
                CountdownTimer(uiState = uiState)
            } else {
                uiState.questionList?.getOrNull(uiState.currentPosition)?.let { TrackDetailsCard(uiState, it) }
                if (uiState.optionList.isNullOrEmpty().not()) {
                    uiState.optionList?.forEach {
                        OptionItem(it)
                    }
                } else {
                    AppText("ai loading")
                }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrackDetailsCard(uiState: GameUiState, track: Track) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = track.md5_image, contentDescription = null, modifier = Modifier
                .size(200.dp)
                .blur(radius = 50.dp)
        )
        Slider(state = SliderState(value = uiState.sliderPosition.toFloat(), valueRange = 0F..10F), colors = SliderDefaults.colors(thumbColor = charcoal))
    }

}

@Composable
private fun OptionItem(option: String) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AppText(
            text = option, modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
                .background(color = charcoal)
                .padding(vertical = 4.dp), textAlign = TextAlign.Center, color = Color.White
        )
    }

}