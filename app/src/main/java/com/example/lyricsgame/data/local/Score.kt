package com.example.lyricsgame.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

@Entity
data class Score(@PrimaryKey val genreId: Int, val category: String?, val score: Int?) {

    fun toViewEntity(): ScoreViewEntity {
        return ScoreViewEntity(genreId = this.genreId, category = this.category, score = this.score)
    }
}
