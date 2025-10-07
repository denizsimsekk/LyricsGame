package com.example.lyricsgame.mediaplayer

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import javax.inject.Inject

class MediaPlayer @Inject constructor(private val context: Context) {

    private var mediaPlayer: ExoPlayer? = null

    private var listener: Player.Listener? = null

    fun setUp(vararg url: String, playWhenReady: Boolean = true) {
        initialize(playWhenReady = playWhenReady, onEvent = {}) {
            val mediaItems = url.map { MediaItem.fromUri(it) }
            addMediaItems(mediaItems)
        }
    }

    private fun initialize(playWhenReady: Boolean = true, onEvent: () -> Unit, setUp: ExoPlayer.() -> Unit) {
        listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                with(player) {
                    onEvent.invoke()
                }
            }
        }
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer = ExoPlayer.Builder(context).build().apply {
            addAnalyticsListener(EventLogger())
            setUp()
            prepare()
            setPlayWhenReady(playWhenReady)
        }
    }

    fun resume() {
        mediaPlayer?.play()
    }

    fun stop() {
        mediaPlayer?.stop()
    }

    fun pause() {
        mediaPlayer?.pause()
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