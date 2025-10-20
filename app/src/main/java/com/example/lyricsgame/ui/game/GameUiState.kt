package com.example.lyricsgame.ui.game

import com.example.lyricsgame.data.model.Track

data class GameUiState(
    var remainingTimeToStartGame: Int = 3,
    var questionList: List<Track>? = listOf(),
    var currentPosition: Int = 0,
    var currentTrack: Track? = null,
    var sliderPosition: Int = 0,
    var optionList: List<String>? = listOf()
)
