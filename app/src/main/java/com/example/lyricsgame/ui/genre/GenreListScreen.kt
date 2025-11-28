package com.example.lyricsgame.ui.genre

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.domain.viewentity.GenreViewEntity
import com.example.lyricsgame.ui.base.BaseScreen
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.navgraph.Route

@Composable
fun GenreListScreen(
    navController: NavController,
    viewModel: GenreListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getGenreList()
    }

    BaseScreen(isTopBarShown = true, topBarTitle = "Genres", navController = navController) {
        Column(modifier = modifier.fillMaxSize()) {
            AppText(
                "Choose a genre to start playing.",
                modifier = modifier.padding(start = 16.dp, top = 16.dp),
                fontWeight = FontWeight.Bold,
                size = 18.sp
            )
            LazyColumn(modifier.fillMaxSize()) {
                items(uiState.genreList) { genre ->
                    GenreItem(genre = genre, navController = navController)
                }
            }
        }
    }


}

@Composable
fun GenreItem(genre: GenreViewEntity, navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clickable {
                navController.navigate(Route.GuessTrackScreen(type = GameType.TRACKS_BY_GENRE, genreId = genre.id, genreName = genre.name))
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        AsyncImage(
            model = genre.picture,
            contentDescription = null,
            modifier = modifier
                .size(96.dp)
                .padding(start = 8.dp, top = 6.dp, bottom = 6.dp, end = 6.dp)
        )

        Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.Center) {
            AppText(genre.name, fontWeight = FontWeight.Bold, size = 18.sp)
        }
    }
}