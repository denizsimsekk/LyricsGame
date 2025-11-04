package com.example.lyricsgame.ui.game

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.lyricsgame.ui.theme.colorCharcoal

@Composable
fun GameScreen(genreId: Int, genreName: String, navController: NavController, viewModel: GameViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent(genreId = genreId, genreName = genreName, navController = navController, viewModel = viewModel)
    }
}

@Composable
private fun MainContent(genreId: Int, genreName: String, navController: NavController, viewModel: GameViewModel) {

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
                    CountdownTimer(uiState = uiState)
                }

                uiState.isQuizFinished -> {
                    // TODO: Score screen
                    AppText("score")
                }

                else -> {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        AnimatedContent(targetState = uiState.remainingTimeToStartGame.toString(), label = "CountDownTimerText") {
                            AppText(
                                text = "$it/${uiState.questionList?.size}",
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
                                OptionItem(option = option, uiState = uiState, viewModel = viewModel)
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
        Slider(
            state = SliderState(value = uiState.sliderPosition.toFloat(), valueRange = 0F..10F),
            colors = SliderDefaults.colors(disabledThumbColor = colorCharcoal, disabledInactiveTickColor = colorCharcoal),
            enabled = false
        )
    }

}

@Composable
private fun OptionItem(option: String, uiState: GameUiState, viewModel: GameViewModel) {

    val animatedAlpha: Float by animateFloatAsState(
        if ((uiState.isCorrectAnswerSelected == true && uiState.selectedTrackTitle == option) || (uiState.isCorrectAnswerSelected == false && uiState.selectedTrackTitle == option)) 1.2f else 1f,
        label = "alpha"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.selectOption(option)
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = option, modifier = Modifier
                .scale(animatedAlpha)
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .fillMaxWidth()
                .background(color = if (uiState.isCorrectAnswerSelected == true && uiState.selectedTrackTitle == option) Color.Green else if (uiState.isCorrectAnswerSelected == false && uiState.selectedTrackTitle == option) Color.Red else colorCharcoal)
                .padding(vertical = 4.dp), textAlign = TextAlign.Center, color = Color.White
        )
    }

}