package com.example.lyricsgame.data.repository

import com.example.lyricsgame.data.local.Score
import com.example.lyricsgame.data.local.ScoreDao
import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : IScoreRepository {

    override fun getScore(type: GameType, genreId: Int?): ScoreViewEntity? {
        return scoreDao.getScore(type = type, genreId = genreId)?.toViewEntity()
    }

    override fun addScore(type: GameType, genreId: Int?, score: Int) {
        scoreDao.insert(Score(type = type, genreId = genreId, score = score))
    }
}