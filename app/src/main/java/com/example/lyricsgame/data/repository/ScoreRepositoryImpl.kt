package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.local.Score
import com.example.lyricsgame.data.local.ScoreDao
import com.example.lyricsgame.domain.repository.IScoreRepository

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : IScoreRepository {

    override fun getScore(): Score {
        TODO("Not yet implemented")
    }

    override fun addScore(score: Score) {
        TODO("Not yet implemented")
    }
}