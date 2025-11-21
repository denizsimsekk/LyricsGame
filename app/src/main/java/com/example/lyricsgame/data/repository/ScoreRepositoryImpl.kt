package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.local.Score
import com.example.lyricsgame.data.local.ScoreDao
import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : IScoreRepository {

    override fun getScore(genreId: Int): ScoreViewEntity? {
        return scoreDao.getScore(genreId = genreId)?.toViewEntity()
    }

    override fun addScore(genreId: Int, score: Int) {
        scoreDao.insert(Score(genreId = genreId, category = null, score = score))
    }
}