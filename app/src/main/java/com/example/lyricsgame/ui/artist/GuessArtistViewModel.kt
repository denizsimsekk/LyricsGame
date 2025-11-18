package com.example.lyricsgame.ui.artist

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.ai.GetAIResponseUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartUseCase
import com.example.lyricsgame.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessArtistViewModel @Inject constructor(private val getGlobalChartUseCase: GetGlobalChartUseCase, private val getAIResponseUseCase: GetAIResponseUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GuessArtistUiState())
    val uiState = _uiState.asStateFlow()

    fun getArtist() {
        updateRemainingTime()
        getGlobalChartUseCase.invoke().getData(onDataReceived = { response ->
            _uiState.update { it.copy(questionList = response?.artists?.data ?: listOf(), questionCount = response?.artists?.data?.size ?: 0) }
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

    fun getQuestionOptions() {
        getAIResponseUseCase.invoke("Top 3 most similar artist name that has similar photo with this ${_uiState.value.currentArtist?.name}.").getData(onDataReceived = { res ->
            _uiState.update { state ->
                val options = res
                    ?.split(";")
                    ?.toMutableList()
                    ?.apply {
                        state.currentArtist?.name?.let { add(it) }
                        shuffle()
                    }

                state.copy(optionList = options, aiError = false)
            }
        }, onError = {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    aiError = true,
                    currentPosition = nextPosition,
                    currentArtist = currentState.questionList?.getOrNull(nextPosition),
                    questionCount = currentState.questionCount.minus(1)
                )
            }
            viewModelScope.launch { getQuestionOptions() }
        }).launchIn(viewModelScope)
    }

}