package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

interface IScoreRepository {

    fun getScore(genreId: Int): ScoreViewEntity?

    fun addScore(genreId: Int, score: Int)

}