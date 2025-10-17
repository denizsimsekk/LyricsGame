package com.example.lyricsgame.ui.home

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.GetGenreListUseCase
import com.example.lyricsgame.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getGenreListUseCase: GetGenreListUseCase) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getGenreList() {
        getGenreListUseCase.invoke().getData (onDataReceived = { response ->
            _uiState.update { it.copy(genreList = response ?: listOf()) }
        }).launchIn(viewModelScope)

    }

}