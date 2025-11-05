package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.local.Score

interface IScoreRepository {

    fun getScore(genreId: Int): Score?

    fun addScore(genreId: Int, score: Int)

}