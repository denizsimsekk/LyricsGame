package com.example.lyricsgame.ui.search

import com.example.lyricsgame.domain.viewentity.ArtistViewEntity

data class SearchUiState(var searchResult: List<ArtistViewEntity>? = null, var searchQuery: String = "", var isActive: Boolean = false)
