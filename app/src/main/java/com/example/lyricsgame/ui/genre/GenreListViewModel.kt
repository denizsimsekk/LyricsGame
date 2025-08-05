package com.example.lyricsgame.ui.genre

import androidx.lifecycle.ViewModel
import com.example.lyricsgame.domain.usecase.GetGenreListUseCase
import com.example.lyricsgame.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(private val getGenreListUseCase: GetGenreListUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getGenreList() {
        _uiState.update { it.copy(genreList = getGenreListUseCase.invoke()) }
    }

}