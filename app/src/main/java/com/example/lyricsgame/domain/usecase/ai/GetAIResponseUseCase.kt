package com.example.lyricsgame.domain.usecase.ai

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.IAIRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAIResponseUseCase @Inject constructor(private val aiRepository: IAIRepository) {

    operator fun invoke(prompt: String): Flow<Resource<String>> {
        return aiRepository.getAiResponse(prompt = prompt)
    }

}