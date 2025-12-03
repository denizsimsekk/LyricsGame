package com.example.lyricsgame.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

@Entity
data class Score(@PrimaryKey val type: GameType, val genreId: Int?, val score: Int?, val artistId: Int?) {

    fun toViewEntity(): ScoreViewEntity {
        return ScoreViewEntity(type = type, genreId = this.genreId, score = this.score, artistId = artistId)
    }
}
