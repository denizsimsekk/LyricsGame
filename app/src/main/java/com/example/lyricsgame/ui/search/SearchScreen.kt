package com.example.lyricsgame.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import com.example.lyricsgame.ui.base.BaseScreen
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.theme.beige
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchScreenViewModel = hiltViewModel()) {
    BaseScreen(isTopBarShown = true, topBarTitle = "Search Your Favourite Artist", navController = navController) {
        MainContent(viewModel = viewModel)
    }
}

@Composable
private fun MainContent(viewModel: SearchScreenViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DebouncedSearchBar(uiState = uiState, viewModel = viewModel)

    if (uiState.searchResult == null) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            AppText("Start searching.")
        }
    } else if (uiState.searchResult?.isEmpty() == true) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            AppText("No result found.")
        }

    } else {
        LazyColumn {
            uiState.searchResult?.let {
                items(it) { item ->
                    SearchItem(item)
                }
            }
        }
    }

}

@Composable
fun DebouncedSearchBar(
    uiState: SearchUiState,
    viewModel: SearchScreenViewModel,
    modifier: Modifier = Modifier,
    debounceTime: Long = 300L
) {
    LaunchedEffect(uiState.searchQuery) {
        delay(debounceTime)
        viewModel.searchArtist()
    }

    OutlinedTextField(
        value = uiState.searchQuery,
        onValueChange = { viewModel.onSearchQueryChanged(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 4.dp, end = 4.dp, bottom = 12.dp),
        placeholder = { AppText("Search...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (uiState.searchQuery.isNotEmpty()) {
                IconButton(onClick = { viewModel.onSearchQueryChanged("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun SearchItem(artist: ArtistViewEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(size = 12.dp))
            .background(beige), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = artist.picture,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(72.dp)
        )
        AppText(text = artist.name)
    }
}