package com.example.lyricsgame.ui.track

import com.example.lyricsgame.data.model.Track

data class GuessTrackUiState(
    var genreId: Int = 0,
    var remainingTimeToStartGame: Int = 3,
    var questionList: List<Track>? = listOf(),
    var currentPosition: Int = 0,
    var currentTrack: Track? = null,
    var selectedTrackTitle: String? = null,
    var sliderPosition: Int = 0,
    var optionList: MutableList<String>? = mutableListOf(),
    var aiError: Boolean = false,
    var isCorrectAnswerSelected: Boolean? = null,
    var isQuizFinished: Boolean = false,
    var correctAnswerCount: Int = 0,
    var questionCount:Int=0,//If the AI cannot generate options for a song, that question should be skipped and the total question count should decrease. The skipped question count should be stored in a separate variable.
    var lastGameScore: Int = 0
)
