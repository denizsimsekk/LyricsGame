package com.example.lyricsgame.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(@PrimaryKey val genreId: Int, val category: String?, val score: Int)
