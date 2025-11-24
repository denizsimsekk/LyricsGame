package com.example.lyricsgame.ui.album

import com.example.lyricsgame.domain.viewentity.AlbumViewEntity
import com.example.lyricsgame.domain.viewentity.GameType

data class GuessAlbumUiState(
    var type: GameType = GameType.ALBUMS,
    var remainingTimeToStartGame: Int = 3,
    var correctAnswerCount: Int = 0,
    var questionCount: Int = 0,
    var isCorrectAnswerSelected: Boolean? = null,
    var questionList: List<AlbumViewEntity> = listOf(),
    var currentPosition: Int = 0,
    var optionList: MutableList<String>? = null,
    var selectedOption: String? = null,
    var currentAlbum: AlbumViewEntity? = null,
    var aiError: Boolean = false,
    var isQuizFinished: Boolean = false,
    var lastGameScore: Int = 0
)
