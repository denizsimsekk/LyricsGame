package com.example.lyricsgame.mediaplayer

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.Listener
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class MediaPlayer @Inject constructor(private val context: Context) {

    private var mediaPlayer: ExoPlayer? = null

    private var listener: Player.Listener? = null

    fun setUp(url: List<String>, onEvent: (Boolean) -> Unit) {
        initialize(onEvent = { state -> onEvent.invoke(state) }) {
            val mediaItems = url.map {
                MediaItem.Builder()
                    .setUri(it)
                    .setClippingConfiguration(
                        MediaItem.ClippingConfiguration.Builder()
                            .setStartPositionMs(0)
                            .setEndPositionMs(10000)
                            .build()
                    )
                    .build()
            }
            addMediaItems(mediaItems)
        }
    }

    private fun initialize(onEvent: (Boolean) -> Unit, setUp: ExoPlayer.() -> Unit) {
        listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                with(player) {
                    onEvent.invoke(isPlaying)
                }
            }
        }
        mediaPlayer = ExoPlayer.Builder(context).build().apply {
            addListener(listener as Listener)
            setUp()
            prepare()
            playWhenReady = false
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun play() {
        mediaPlayer?.play()
    }

    fun seekToNextSong() {
        mediaPlayer?.seekToNextMediaItem()
    }

    fun release() {
        mediaPlayer?.apply {
            listener?.let { removeListener(it) }
            this.release()
        }
        mediaPlayer = null
        listener = null
    }

}