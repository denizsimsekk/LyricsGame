package com.example.lyricsgame.ui.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lyricsgame.ui.theme.robotoFamily
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lyricsgame.data.model.Genre
import com.example.lyricsgame.ui.navgraph.Route

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
            .padding(16.dp)
    ) {
        Text(
            "Welcome to the Guess The Lyrics!",
            style = TextStyle(fontFamily = robotoFamily, fontWeight = FontWeight.Bold)
        )
        Row(
            modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Explore Music Genres", fontFamily = robotoFamily, fontWeight = FontWeight.Bold)

            Button(
                onClick = {
                }, shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("Discover", fontFamily = robotoFamily)

            }
        }
        LazyRow {
            items(uiState.genreList) { item ->
                GenreItem(modifier, item) {
                    navController.navigate(Route.GameScreen.route)
                }
            }
        }
    }
}

@Composable
fun GenreItem(modifier: Modifier, genre: Genre, onGenreSelected: () -> Unit) {
    Image(
        painter = painterResource(genre.cover),
        contentDescription = null,
        modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable {
                onGenreSelected.invoke()
            }
    )
}
