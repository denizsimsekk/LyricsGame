package com.example.lyricsgame.ui.track

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Track
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.common.AppTopBar
import com.example.lyricsgame.ui.common.CountdownTimerText
import com.example.lyricsgame.ui.common.OptionItem
import com.example.lyricsgame.ui.theme.colorCharcoal

@Composable
fun GuessTrackScreen(genreId: Int, genreName: String, navController: NavController, viewModel: GuessTrackViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent(genreId = genreId, genreName = genreName, navController = navController, viewModel = viewModel)
    }
}

@Composable
private fun MainContent(genreId: Int, genreName: String, navController: NavController, viewModel: GuessTrackViewModel) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGenreSongList(genreId = genreId)
    }

    LaunchedEffect(uiState.remainingTimeToStartGame) {
        if (uiState.remainingTimeToStartGame <= 0) {
            viewModel.getQuestionOptions()
        }
    }

    if (uiState.aiError) {
        Toast.makeText(context, "AI Error Occured🤯Let's continue with next song🚀", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        AppTopBar(
            title = genreName
        ) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            when {
                uiState.remainingTimeToStartGame > 0 -> {
                    CountdownTimerText(remainingTime = uiState.remainingTimeToStartGame)
                }

                uiState.isQuizFinished -> {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        AppText(
                            text = "\uD83D\uDCAB${uiState.correctAnswerCount}/${uiState.questionCount}\uD83D\uDCAB",
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

                else -> {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        if (uiState.lastGameScore != 0) {
                            AppText(
                                text = "Your best score so far⭐${uiState.lastGameScore}⭐",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                size = 12.sp
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                            )
                        }
                        AnimatedContent(targetState = uiState.correctAnswerCount.toString(), label = "CountDownTimerText") {
                            AppText(
                                text = "$it/${uiState.questionCount}",
                                fontWeight = FontWeight.Bold,
                                color = if (uiState.isCorrectAnswerSelected == true) Color.Green else Color.Black,
                                size = 24.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                                .fillMaxWidth()
                        )

                        val currentTrack = uiState.questionList?.getOrNull(uiState.currentPosition)

                        currentTrack?.let {
                            TrackDetailsCard(uiState, it)
                        }
                        if (uiState.optionList.isNullOrEmpty().not()) {
                            uiState.optionList!!.forEach { option ->
                                OptionItem(option = option, isCorrectAnswer = uiState.isCorrectAnswerSelected, selectedOption = uiState.selectedTrackTitle) {
                                    viewModel.selectOption(option)
                                }
                            }
                        } else {
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
                                model = R.drawable.ai_loading,
                                contentDescription = null,
                                imageLoader = imageLoader,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrackDetailsCard(uiState: GuessTrackUiState, track: Track) {
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
        Slider(
            state = SliderState(value = uiState.sliderPosition.toFloat(), valueRange = 0F..10F),
            colors = SliderDefaults.colors(disabledThumbColor = colorCharcoal, disabledInactiveTickColor = colorCharcoal),
            enabled = false
        )
    }

}
