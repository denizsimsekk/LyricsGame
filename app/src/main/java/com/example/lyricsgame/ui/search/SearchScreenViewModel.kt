package com.example.lyricsgame.ui.search

import androidx.lifecycle.viewModelScope
import com.example.lyricsgame.domain.usecase.search.SearchArtistUseCase
import com.example.lyricsgame.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val searchArtistUseCase: SearchArtistUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun searchArtist() {
        searchArtistUseCase.invoke(query = _uiState.value.searchQuery).getData(onDataReceived = { res ->
            _uiState.update { it.copy(searchResult = res) }
        }).launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        if (_uiState.value.searchQuery.isEmpty().not()) {
            searchArtist()
        } else {
            _uiState.update { it.copy(searchResult = null) }
        }
    }

}