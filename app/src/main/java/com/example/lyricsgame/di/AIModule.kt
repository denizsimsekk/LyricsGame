package com.example.lyricsgame.di

import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AiModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        return Firebase.ai(backend = GenerativeBackend.googleAI())
            .generativeModel(modelName = "gemini-2.5-flash", systemInstruction = content {
                text("You are a music similarity expert. If asking TRACK, Respond ONLY with the song titles separated by a semicolon. If asking ARTIST, Respond ONLY with the artist names separated by a semicolon . Do not use numbering, explanation, artist name or any extra text.")
            })
    }
}