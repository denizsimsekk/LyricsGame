package com.example.lyricsgame.ui.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var timer: Timer? = null
    private var remainingSeconds = 3

    fun updateRemainingTime() {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            remainingSeconds--
            _uiState.update {
                it.copy(remainingTimeToStartGame = remainingSeconds)
            }

            if (remainingSeconds < 0) {
                this.cancel()
            }
        }
    }
}
//TODO flatmapConcat https://medium.com/@myofficework000/7-kotlin-flow-operators-that-you-must-know-62eb726e3ff4 might use that