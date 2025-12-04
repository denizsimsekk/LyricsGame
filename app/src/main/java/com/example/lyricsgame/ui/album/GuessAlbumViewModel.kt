package com.example.lyricsgame.ui.album

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.ai.GetAIResponseUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartAlbumListUseCase
import com.example.lyricsgame.domain.usecase.score.GetScoreUseCase
import com.example.lyricsgame.domain.usecase.score.SaveScoreUseCase
import com.example.lyricsgame.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessAlbumViewModel @Inject constructor(
    private val getGlobalChartAlbumListUseCase: GetGlobalChartAlbumListUseCase,
    private val getScoreUseCase: GetScoreUseCase,
    private val saveScoreUseCase: SaveScoreUseCase,
    private val getAIResponseUseCase: GetAIResponseUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GuessAlbumUiState())
    val uiState = _uiState.asStateFlow()

    fun getAlbum() {
        updateRemainingTime()
        getGlobalChartAlbumListUseCase.invoke().getData(onDataReceived = { response ->
            val lastScore = getScoreUseCase.invoke(type = _uiState.value.type)
            _uiState.update { it.copy(questionList = response ?: listOf(), currentAlbum = response?.getOrNull(0), questionCount = response?.size ?: 0, lastGameScore = lastScore?.score ?: 0) }
            getQuestionOptions()
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
        _uiState.update { state ->
            val isCorrect = option == state.currentAlbum?.title
            state.copy(
                selectedOption = option,
                isCorrectAnswerSelected = isCorrect,
                correctAnswerCount = if (isCorrect) state.correctAnswerCount + 1 else state.correctAnswerCount
            )
        }
        viewModelScope.launch {
            delay(500)
            proceedToNextQuestion()
        }
    }

    private fun proceedToNextQuestion() {
        if (_uiState.value.questionList.last() == _uiState.value.currentAlbum) {
            _uiState.update { it.copy(isQuizFinished = true) }
            saveScoreUseCase.invoke(type = _uiState.value.type, score = _uiState.value.correctAnswerCount)
        } else {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    currentPosition = nextPosition,
                    currentAlbum = currentState.questionList?.getOrNull(nextPosition),
                    optionList = null,
                    isCorrectAnswerSelected = null,
                    selectedOption = null
                )
            }
            getQuestionOptions()
        }
    }

    private fun getQuestionOptions() {
        getAIResponseUseCase.invoke("Top 3 most similar album name that has similar cover photo with this ${_uiState.value.currentAlbum?.title}.").getData(onDataReceived = { res ->
            _uiState.update { state ->
                val options = res
                    ?.split(";")
                    ?.toMutableList()
                    ?.apply {
                        state.currentAlbum?.title?.let { add(it) }
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
                    currentAlbum = currentState.questionList?.getOrNull(nextPosition),
                    questionCount = currentState.questionCount.minus(1)
                )
            }
            viewModelScope.launch { getQuestionOptions() }
        }).launchIn(viewModelScope)
    }

}