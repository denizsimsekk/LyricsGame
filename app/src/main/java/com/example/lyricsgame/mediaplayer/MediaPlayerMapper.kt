package com.example.lyricsgame.mediaplayer

import androidx.media3.common.Player

private fun Int.toPlayerState(isPlaying: Boolean): MediaPlayerState {
    return when (this) {
        Player.STATE_IDLE -> MediaPlayerState.IDLE
        Player.STATE_ENDED -> MediaPlayerState.ENDED
        else -> if (isPlaying) MediaPlayerState.PLAYING else MediaPlayerState.PAUSED
    }
}