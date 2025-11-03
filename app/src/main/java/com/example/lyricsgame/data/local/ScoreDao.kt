package com.example.lyricsgame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert
    fun insert(score: Score)

    @Query("SELECT * FROM score")
    fun getScore(): Score

}