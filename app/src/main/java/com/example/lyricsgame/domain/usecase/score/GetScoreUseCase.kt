package com.example.lyricsgame.domain.usecase.score

import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.viewentity.ScoreViewEntity
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(private val scoreRepository: IScoreRepository) {

    operator fun invoke(genreId: Int): ScoreViewEntity? {
        return scoreRepository.getScore(genreId = genreId)
    }

}