package com.example.lyricsgame.ui.artist

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.lyricsgame.ui.base.BaseScreen
import com.example.lyricsgame.ui.common.AILoadingPlaceholder
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.common.BlurredImage
import com.example.lyricsgame.ui.common.CountdownTimerText
import com.example.lyricsgame.ui.common.OptionItem
import com.example.lyricsgame.ui.common.ScoreSection

@Composable
fun GuessArtistScreen(viewModel: GuessArtistViewModel = hiltViewModel(), navController: NavController) {
    BaseScreen(isTopBarShown = true, topBarTitle = "Artists", navController = navController) {
        MainContent(viewModel = viewModel)
    }
}

@Composable
private fun MainContent(viewModel: GuessArtistViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getArtist()
    }

    if (uiState.aiError) {
        Toast.makeText(context, "AI Error Occurred🤯Let's continue with next song🚀", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            when {
                uiState.remainingTimeToStartGame > 0 -> {
                    CountdownTimerText(remainingTime = uiState.remainingTimeToStartGame)
                }

                uiState.isQuizFinished -> {
                    ScoreSection(correctAnswerCount = uiState.correctAnswerCount, questionCount = uiState.questionCount)
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

                        uiState.currentArtist?.let {
                            BlurredImage(url = it.picture)
                        }

                        val options = uiState.optionList

                        if (options.isNullOrEmpty()) {
                            AILoadingPlaceholder()
                            return
                        }

                        options.forEach { option ->
                            OptionItem(
                                option = option,
                                isCorrectAnswer = uiState.isCorrectAnswerSelected,
                                selectedOption = uiState.selectedOption
                            ) {
                                viewModel.selectOption(option)
                            }
                        }
                    }
                }
            }
        }
    }
}
