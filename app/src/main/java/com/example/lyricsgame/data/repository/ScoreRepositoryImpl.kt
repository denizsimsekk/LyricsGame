package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.local.Score
import com.example.lyricsgame.data.local.ScoreDao
import com.example.lyricsgame.domain.repository.IScoreRepository

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : IScoreRepository {

    override fun getScore(genreId: Int): Score? {
        return scoreDao.getScore(genreId = genreId)
    }

    override fun addScore(genreId: Int, score: Int) {
        scoreDao.insert(Score(genreId = genreId, category = null, score = score))
    }
}