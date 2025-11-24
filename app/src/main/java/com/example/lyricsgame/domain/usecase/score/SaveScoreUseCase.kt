package com.example.lyricsgame.domain.usecase.score

import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.viewentity.GameType
import javax.inject.Inject

class SaveScoreUseCase @Inject constructor(private val scoreRepository: IScoreRepository) {

    operator fun invoke(type: GameType, genreId: Int?=null, score: Int) {
        scoreRepository.addScore(type = type, genreId = genreId, score = score)
    }

}