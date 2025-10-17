package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface IAIRepository {

    fun getAiResponse(prompt: String):  Flow<Resource<String>>

}