package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

interface IScoreRepository {

    fun getScore(type: GameType, genreId: Int?): ScoreViewEntity?

    fun addScore(type: GameType, genreId: Int?, score: Int)

}