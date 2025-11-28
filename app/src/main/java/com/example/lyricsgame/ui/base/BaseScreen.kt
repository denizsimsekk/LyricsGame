package com.example.lyricsgame.ui.base

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.lyricsgame.ui.common.AppTopBar

@Composable
fun BaseScreen(isTopBarShown: Boolean = false, topBarTitle: String? = null, viewModel: BaseViewModel = hiltViewModel(), navController: NavController? = null, content: @Composable () -> Unit) {

    val uiState by viewModel.baseUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (uiState.errorMessage.isNullOrEmpty().not()) {
        Toast.makeText(context, "AI Error Occured🤯Let's continue with next song🚀", Toast.LENGTH_SHORT).show()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            if (isTopBarShown) {
                AppTopBar(
                    title = topBarTitle ?: ""
                ) {
                    navController?.popBackStack()
                }
            }

            content.invoke()

        }
    }
}