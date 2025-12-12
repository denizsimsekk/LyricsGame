package com.example.lyricsgame.domain.repository

import com.example.lyricsgame.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface IAIRepository {

    fun getAiResponse(prompt: String):  Flow<Resource<String>>

}