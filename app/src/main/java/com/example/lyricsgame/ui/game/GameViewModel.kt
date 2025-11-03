package com.example.lyricsgame.ui.game

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.repository.IAIRepository
import com.example.lyricsgame.domain.usecase.track.GetTopTrackListByGenreUseCase
import com.example.lyricsgame.mediaplayer.MediaPlayer
import com.example.lyricsgame.mediaplayer.MediaPlayerState
import com.example.lyricsgame.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getTopTrackListByGenreUseCase: GetTopTrackListByGenreUseCase,
    private val aiRepository: IAIRepository,
    private val mediaPlayer: MediaPlayer
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var updateJob: Job? = null

    fun getGenreSongList(genreId: Int) {
        updateRemainingTime()
        getTopTrackListByGenreUseCase.invoke(genreId = genreId).getData(onDataReceived = { response ->
            _uiState.update {
                it.copy(questionList = response, currentTrack = response?.getOrNull(0))
            }
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

    fun getQuestionOptions() {
        aiRepository.getAiResponse("Top 3 most similar songs to ${_uiState.value.currentTrack?.title} by ${_uiState.value.currentTrack?.artist?.name}.").getData(onDataReceived = { res ->
            _uiState.update { state ->
                val options = res
                    ?.split(";")
                    ?.toMutableList()
                    ?.apply {
                        state.currentTrack?.title?.let { add(it) }
                        shuffle()
                    }

                state.copy(optionList = options)
            }
            play()
        }, onError = {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    aiError = true,
                    currentPosition = nextPosition,
                    currentTrack = currentState.questionList?.getOrNull(nextPosition)
                )
            }
            viewModelScope.launch { getQuestionOptions() }
        }).launchIn(viewModelScope)
    }

    private fun play() {
        _uiState.value.currentTrack?.let {
            mediaPlayer.setUp(it.preview, onEvent = { playerState ->
                if (playerState == MediaPlayerState.PLAYING) {
                    updateJob?.cancel()
                    updateJob = viewModelScope.launch(Dispatchers.Main) {
                        _uiState.update { currentState -> currentState.copy(sliderPosition = 0, aiError = false) }
                        while (isActive && _uiState.value.sliderPosition < 10) {
                            delay(1000)
                            _uiState.update { current ->
                                current.copy(sliderPosition = current.sliderPosition + 1)
                            }
                        }
                    }
                }
            })
        }
    }

    fun selectOption(trackTitle: String) {
        _uiState.update { it.copy(selectedTrackTitle = trackTitle, isTrueAnswerSelected = trackTitle == _uiState.value.currentTrack?.title) }
        viewModelScope.launch {
            delay(500)
            proceedToNextSong()
        }
    }

    private fun proceedToNextSong() {
        if (_uiState.value.questionList?.last() == _uiState.value.currentTrack) {
            _uiState.update { it.copy(isQuizFinished = true) }
        } else {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    currentPosition = nextPosition,
                    currentTrack = currentState.questionList?.getOrNull(nextPosition),
                    optionList = null,
                    sliderPosition = 0,
                    isTrueAnswerSelected = null,
                    selectedTrackTitle = null
                )
            }
            getQuestionOptions()
        }
        updateJob?.cancel()
        mediaPlayer.stop()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.release()
        updateJob?.cancel()
    }
}
