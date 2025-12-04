package com.example.lyricsgame.ui.track

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.ai.GetAIResponseUseCase
import com.example.lyricsgame.domain.usecase.artist.GetArtistTrackListUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartTrackListUseCase
import com.example.lyricsgame.domain.usecase.score.GetScoreUseCase
import com.example.lyricsgame.domain.usecase.score.SaveScoreUseCase
import com.example.lyricsgame.domain.usecase.track.GetTopTrackListByGenreUseCase
import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.mediaplayer.MediaPlayer
import com.example.lyricsgame.mediaplayer.MediaPlayerState
import com.example.lyricsgame.ui.base.BaseViewModel
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
class GuessTrackViewModel @Inject constructor(
    private val getTopTrackListByGenreUseCase: GetTopTrackListByGenreUseCase,
    private val getGlobalChartTrackListUseCase: GetGlobalChartTrackListUseCase,
    private val getArtistTrackListUseCase: GetArtistTrackListUseCase,
    private val getAIResponseUseCase: GetAIResponseUseCase,
    private val getScoreUseCase: GetScoreUseCase,
    private val saveScoreUseCase: SaveScoreUseCase,
    private val mediaPlayer: MediaPlayer
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(GuessTrackUiState())
    val uiState = _uiState.asStateFlow()

    private var updateJob: Job? = null

    fun getGenreSongList(type: GameType, genreId: Int?, artistId: Int? = null) {
        updateRemainingTime()
        val score = when (type) {
            GameType.GLOBAL_TRACKS -> getScoreUseCase.invoke(type, null)
            GameType.TRACKS_BY_ARTIST -> getScoreUseCase.invoke(type, null, artistId)
            else -> getScoreUseCase.invoke(type, genreId ?: 0)
        }

        val source = when (type) {
            GameType.GLOBAL_TRACKS ->
                getGlobalChartTrackListUseCase.invoke()

            GameType.TRACKS_BY_ARTIST ->
                artistId?.let { getArtistTrackListUseCase.invoke(it) }

            else ->
                getTopTrackListByGenreUseCase.invoke(genreId ?: 0)
        } ?: return

        source.getData(onDataReceived = { response ->
            val list = response ?: emptyList()

            _uiState.update {
                it.copy(
                    type = type,
                    genreId = genreId,
                    artistId = artistId,
                    questionList = list,
                    currentTrack = list.getOrNull(0),
                    questionCount = list.size,
                    lastGameScore = score?.score ?: 0
                )
            }
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

    private fun getQuestionOptions() {
        getAIResponseUseCase.invoke("Top 3 most similar songs to ${_uiState.value.currentTrack?.title} by ${_uiState.value.currentTrack?.artist?.name}.").getData(onDataReceived = { res ->
            _uiState.update { state ->
                val options = res
                    ?.split(";")
                    ?.toMutableList()
                    ?.apply {
                        state.currentTrack?.title?.let { add(it) }
                        shuffle()
                    }

                state.copy(optionList = options, aiError = false)
            }
            play()
        }, onError = {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    aiError = true,
                    currentPosition = nextPosition,
                    currentTrack = currentState.questionList?.getOrNull(nextPosition),
                    questionCount = currentState.questionCount.minus(1)
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

    fun selectOption(trackTitle: String) {
        _uiState.update { state ->
            val isCorrect = trackTitle == state.currentTrack?.title
            state.copy(
                selectedTrackTitle = trackTitle,
                isCorrectAnswerSelected = isCorrect,
                correctAnswerCount = if (isCorrect) state.correctAnswerCount + 1 else state.correctAnswerCount
            )
        }
        viewModelScope.launch {
            delay(500)
            proceedToNextSong()
        }
    }

    private fun proceedToNextSong() {
        if (_uiState.value.questionList?.last() == _uiState.value.currentTrack) {
            _uiState.update { it.copy(isQuizFinished = true) }
            _uiState.value.type?.let {
                saveScoreUseCase.invoke(
                    type = it,
                    genreId = _uiState.value.genreId,
                    score = _uiState.value.correctAnswerCount,
                    artistId = _uiState.value.artistId
                )
            }
        } else {
            _uiState.update { currentState ->
                val nextPosition = currentState.currentPosition + 1
                currentState.copy(
                    currentPosition = nextPosition,
                    currentTrack = currentState.questionList?.getOrNull(nextPosition),
                    optionList = null,
                    sliderPosition = 0,
                    isCorrectAnswerSelected = null,
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
