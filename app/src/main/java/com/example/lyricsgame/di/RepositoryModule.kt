package com.example.lyricsgame.di

import android.content.Context
import com.example.lyricsgame.data.repository.GenreRepositoryImpl
import com.example.lyricsgame.domain.repository.IGenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(@ApplicationContext context: Context): IGenreRepository =
        GenreRepositoryImpl(context)

}