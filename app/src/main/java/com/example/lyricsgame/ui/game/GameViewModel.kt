package com.example.lyricsgame.ui.game

import androidx.lifecycle.viewModelScope
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
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getTopTrackListByGenreUseCase: GetTopTrackListByGenreUseCase,
    private val mediaPlayer: MediaPlayer
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var timer: Timer? = null
    private var updateJob: Job? = null

    private var remainingSeconds = 3

    fun getGenreSongList(genreId: Int) {
        updateRemainingTime()
        getTopTrackListByGenreUseCase.invoke(genreId = genreId).getData { response ->
            _uiState.update {
                it.copy(trackList = response)
            }
        }.launchIn(viewModelScope)
    }

    private fun updateRemainingTime() {
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

    fun play() {
        _uiState.value.trackList?.get(_uiState.value.currentPosition)?.let {
            mediaPlayer.setUp(it.preview, onEvent = { playerState ->
                if (playerState == MediaPlayerState.ENDED) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            currentPosition = uiState.value.currentPosition + 1,
                            currentTrack = uiState.value.trackList?.getOrNull(uiState.value.currentPosition + 1)
                        )
                    }
                }
                if (playerState == MediaPlayerState.PLAYING) {
                    updateJob?.cancel()
                    updateJob = viewModelScope.launch(Dispatchers.Main) {
                        _uiState.update { currentState -> currentState.copy(sliderPosition = 0) }

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
    }
}
