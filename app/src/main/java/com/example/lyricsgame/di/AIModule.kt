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
                text("You are a Music Similarity Expert AI; if the request contains TRACK respond only with song titles separated by semicolons, if ARTIST respond only with artist names separated by semicolons, if ALBUM respond only with album names separated by semicolons; no numbering, no explanation, no quotes, no repetition of input, no extra text—output only the final result.")
            })
    }
}