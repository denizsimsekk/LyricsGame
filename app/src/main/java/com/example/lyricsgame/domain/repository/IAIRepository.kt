package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Resource
import kotlinx.coroutines.flow.Flow

// Direct repository access is fine here — fetching AI response is a technical operation,
// not a user-initiated or domain-level action, so no UseCase abstraction is needed.
interface IAIRepository {

    fun getAiResponse(prompt: String):  Flow<Resource<String>>

}