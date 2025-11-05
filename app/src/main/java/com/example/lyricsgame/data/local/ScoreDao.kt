package com.example.lyricsgame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(score: Score)

    @Query("SELECT * FROM score WHERE genreId=:genreId")
    fun getScore(genreId: Int): Score?

}