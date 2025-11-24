package com.example.lyricsgame.domain.usecase.score

import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.viewentity.GameType
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(private val scoreRepository: IScoreRepository) {

    operator fun invoke(type: GameType, genreId: Int? = null): ScoreViewEntity? {
        return scoreRepository.getScore(type = type, genreId = genreId)
    }

}