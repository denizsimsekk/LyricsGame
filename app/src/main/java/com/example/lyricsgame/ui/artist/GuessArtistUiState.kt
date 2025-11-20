package com.example.lyricsgame.ui.artist

import com.example.lyricsgame.data.model.Artist

data class GuessArtistUiState(
    var type: Int = -1,
    var remainingTimeToStartGame: Int = 3,
    var correctAnswerCount: Int = 0,
    var questionCount: Int = 0,
    var isCorrectAnswerSelected: Boolean? = null,
    var questionList: List<Artist> = listOf(),
    var currentPosition: Int = 0,
    var optionList: MutableList<String>? = null,
    var selectedOption: String? = null,
    var currentArtist: Artist? = null,
    var aiError: Boolean = false,
    var isQuizFinished: Boolean = false,
    var lastGameScore: Int = 0
)
