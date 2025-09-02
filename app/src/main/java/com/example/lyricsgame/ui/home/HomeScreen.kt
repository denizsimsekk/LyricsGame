package com.example.lyricsgame.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lyricsgame.R
import com.example.lyricsgame.data.model.Genre
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
            .verticalScroll(rememberScrollState())
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
        GenreListSection(uiState = uiState, navController = navController)
        GlobalChartSection(navController = navController)
        SearchYourFavoriteArtistsSection(navController = navController)


    }
}

@Composable
private fun GenreListSection(uiState: HomeUiState, navController: NavController, modifier: Modifier = Modifier) {
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
                text = "Discover More",
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

@Composable
private fun GlobalChartSection(navController: NavController, modifier: Modifier = Modifier) {
    AppText("Explore Global Chart", fontWeight = FontWeight.Bold, modifier = modifier.padding(16.dp))
    Image(painter = painterResource(R.drawable.global_chart_banner), contentDescription = null, modifier = modifier.padding(horizontal = 16.dp))
}

@Composable
private fun SearchYourFavoriteArtistsSection(navController: NavController, modifier: Modifier = Modifier) {
    AppText("List Your Favorite Artists", fontWeight = FontWeight.Bold, modifier = modifier.padding(16.dp))
    Image(painter = painterResource(R.drawable.search_artists_banner), contentDescription = null, modifier = modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        .clickable {
            navController.navigate(Route.SearchScreen)
        })
}

@Composable
private fun GenreItem(modifier: Modifier, genre: Genre, onGenreSelected: () -> Unit) {
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
