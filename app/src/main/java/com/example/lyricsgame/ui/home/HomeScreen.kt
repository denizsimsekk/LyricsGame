package com.example.lyricsgame.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lyricsgame.domain.model.GenreViewEntity
import com.example.lyricsgame.ui.common.AppText
import com.example.lyricsgame.ui.navgraph.Route
import com.example.lyricsgame.ui.theme.charcoal

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    MainContent(modifier = modifier, navController = navController)
}

@Composable
fun MainContent(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getGenreList()
    }
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AppText(
                text = "Welcome to the Guess The Lyrics!",
                fontWeight = FontWeight.SemiBold,
                size = 18.sp,
                modifier = modifier.padding(horizontal = 8.dp),
                color = Color.White
            )
        }
        Row(
            modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText("Explore Music Genres", fontWeight = FontWeight.Bold)

            Button(
                onClick = {
                }, shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = charcoal),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                AppText(
                    text = "Discover",
                    color = Color.White,
                    modifier = modifier.clickable { navController.navigate(Route.GenreListScreen) })

            }
        }
        LazyRow(modifier = modifier.padding(horizontal = 16.dp)) {
            items(uiState.genreList) { item ->
                GenreItem(modifier, item) {
                    navController.navigate(Route.GameScreen(item.id))
                }
            }
        }
    }
}

@Composable
fun GenreItem(modifier: Modifier, genre: GenreViewEntity, onGenreSelected: () -> Unit) {
    AsyncImage(
        model = genre.picture,
        contentDescription = null,
        modifier = modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable {
                onGenreSelected.invoke()
            }
    )
}
