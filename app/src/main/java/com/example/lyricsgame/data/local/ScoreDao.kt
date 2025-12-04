package com.example.lyricsgame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lyricsgame.domain.viewentity.GameType

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(score: Score)

    @Query("SELECT * FROM score  WHERE type = :type AND (:genreId IS NULL OR genreId = :genreId) AND (:artistId IS NULL OR artistId = :artistId)")
    fun getScore(type: GameType, genreId: Int?,artistId:Int?): Score?

}