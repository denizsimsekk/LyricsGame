package com.example.lyricsgame.ui.game

import com.example.lyricsgame.data.model.Track

data class GameUiState(
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
    var questionCount:Int=0,//if ai couldn't generate options for the song it should be count from questionList so should store in another variable
    var lastGameScore: Int = 0
)
