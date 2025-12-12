package com.example.lyricsgame.data.repository

import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.repository.IAIRepository
import com.google.firebase.ai.GenerativeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AIRepositoryImpl @Inject constructor (private val model:GenerativeModel): IAIRepository {

    override fun getAiResponse(prompt: String): Flow<Resource<String>> {
        return flow {
            try {
                val generatedContent = model.generateContent(prompt)
                val text = generatedContent.text
                if (!text.isNullOrBlank()) {
                    emit(Resource.Success(text))
                } else {
                    emit(Resource.Failure("AI returned empty response"))
                }
            } catch (e: Exception) {
                println("AI error: ${e.localizedMessage}")
                emit(Resource.Failure(e.localizedMessage ?: "Unknown AI error"))
            }
        }
    }

}