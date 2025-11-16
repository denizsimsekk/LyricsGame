package com.example.lyricsgame.ui.artist

import com.example.lyricsgame.data.model.Artist

data class GuessArtistUiState(
    var remainingTimeToStartGame: Int = 3,
    var correctAnswerCount: Int = 0,
    var questionCount: Int = 0,
    var isCorrectAnswerSelected: Boolean? = null,
    var questionList: List<Artist> = listOf(),
    var currentPosition: Int = 0,
    var optionList: List<String> = listOf(),
    var selectedOption: String? = null,
)
