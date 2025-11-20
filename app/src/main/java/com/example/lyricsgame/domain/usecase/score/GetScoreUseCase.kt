package com.example.lyricsgame.domain.usecase.score

import com.example.lyricsgame.data.local.Score
import com.example.lyricsgame.domain.repository.IScoreRepository
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(private val scoreRepository: IScoreRepository) {

    operator fun invoke(genreId: Int): Score? {
        return scoreRepository.getScore(genreId = genreId)
    }

}