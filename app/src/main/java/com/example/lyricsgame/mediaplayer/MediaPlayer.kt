package com.example.lyricsgame.mediaplayer

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.Listener
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import javax.inject.Inject

class MediaPlayer @Inject constructor(private val context: Context) {

    private var mediaPlayer: ExoPlayer? = null

    private var listener: Player.Listener? = null

    val currentPosition = mediaPlayer?.currentPosition


    fun setUp(vararg url: String, playWhenReady: Boolean = true, onEvent: (Boolean) -> Unit) {
        initialize(playWhenReady = playWhenReady, onEvent = { state -> onEvent.invoke(state) }) {
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

    private fun initialize(playWhenReady: Boolean = true, onEvent: (Boolean) -> Unit, setUp: ExoPlayer.() -> Unit) {
        listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                with(player) {
                    onEvent.invoke(isPlaying)
                }
            }
        }
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer = ExoPlayer.Builder(context).build().apply {
            addListener(listener as Listener)
            addAnalyticsListener(EventLogger())
            setUp()
            prepare()
            setPlayWhenReady(playWhenReady)
        }
    }

    fun stop() {
        mediaPlayer?.stop()
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