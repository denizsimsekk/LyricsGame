package com.example.lyricsgame.domain.usecase.score

import com.example.lyricsgame.domain.repository.IScoreRepository
import javax.inject.Inject

class SaveScoreUseCase @Inject constructor(private val scoreRepository: IScoreRepository) {

    operator fun invoke(genreId: Int, score: Int) {
        scoreRepository.addScore(genreId = genreId, score = score)
    }

}