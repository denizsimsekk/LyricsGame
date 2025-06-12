package com.example.lyricsgame.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.domain.usecase.GetGenreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getGenreListUseCase: GetGenreListUseCase) :
    ViewModel() {

    private val _genreList = MutableStateFlow<List<Genre>>(listOf())
    val genreList = _genreList.asStateFlow()

    fun getGenreList() {
        viewModelScope.launch {
            getGenreListUseCase.invoke().collect {
                _genreList.emit(it)
            }
        }
    }

}