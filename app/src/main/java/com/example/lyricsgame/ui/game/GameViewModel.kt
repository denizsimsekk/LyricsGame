package com.example.lyricsgame.ui.game

import android.util.Log
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
import kotlinx.coroutines.withContext
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

    fun getAiResponse() {
        aiRepository.getAiResponse("Top 4 most similar songs to ${_uiState.value.currentTrack?.title} by ${_uiState.value.currentTrack?.artist?.name}. Just answers seperated by ,").getData(onDataReceived = { res ->
            _uiState.update {
                it.copy(aiResponse = res ?: "")
            }
            play()
        }).launchIn(viewModelScope)
    }

    fun play() {
        _uiState.value.questionList?.get(_uiState.value.currentPosition)?.let {
            mediaPlayer.setUp(it.preview, onEvent = { playerState ->
                if (playerState == MediaPlayerState.PLAYING) {
                    updateJob?.cancel()
                    updateJob = viewModelScope.launch(Dispatchers.Main) {
                        _uiState.update { currentState -> currentState.copy(sliderPosition = 0) }
                        println("aiRes: ${_uiState.value.aiResponse}")
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

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.release()
        updateJob?.cancel()
    }
}
