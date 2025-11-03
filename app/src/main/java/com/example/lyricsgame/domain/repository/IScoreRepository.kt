package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.local.Score

interface IScoreRepository {

    fun getScore(): Score

    fun addScore(score: Score)

}