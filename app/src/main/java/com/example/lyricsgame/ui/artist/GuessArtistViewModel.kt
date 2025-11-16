package com.example.lyricsgame.ui.artist

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartUseCase
import com.example.lyricsgame.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessArtistViewModel @Inject constructor(private val getGlobalChartUseCase: GetGlobalChartUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GuessArtistUiState())
    val uiState = _uiState.asStateFlow()

    fun getArtist() {
        updateRemainingTime()
        getGlobalChartUseCase.invoke().getData(onDataReceived = { response ->
            _uiState.update { it.copy(questionList = response?.artists?.data ?: listOf()) }
        }).launchIn(viewModelScope)
    }

    private fun updateRemainingTime() {
        viewModelScope.launch {
            var remaining = _uiState.value.remainingTimeToStartGame
            while (remaining >= 0) {
                _uiState.update { it.copy(remainingTimeToStartGame = remaining) }
                delay(1000L)
                remaining--
            }
        }
    }

    fun selectOption(option: String) {

    }

}