package com.example.lyricsgame.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lyricsgame.ui.navgraph.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Button(modifier = Modifier.padding(20.dp),
        onClick = {
            navController.navigate(Screen.GAME_SCREEN.name)
        }, content = { Text("go") })
}