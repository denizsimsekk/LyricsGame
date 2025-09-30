package com.example.lyricsgame.ui.game

import com.example.lyricsgame.data.model.Track

data class GameUiState(var remainingTimeToStartGame: Int = 3, var trackList: List<Track>? = listOf())
