package com.example.lyricsgame.ui.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var timer: Timer? = null
    private var remainingSeconds = 2

    fun updateRemainingTime() {
        timer = fixedRateTimer(initialDelay = 3000L, period = 1000L) {
            _uiState.update {
                it.copy(formattedRemainingTimeToStartGame = remainingSeconds.toString())
            }
            remainingSeconds--

            if (remainingSeconds < 0) {
                this.cancel()
            }
        }
    }
}