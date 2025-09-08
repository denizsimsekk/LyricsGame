package com.example.lyricsgame.di

import android.content.Context
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.data.repository.ArtistRepositoryImpl
import com.example.lyricsgame.data.repository.GenreRepositoryImpl
import com.example.lyricsgame.domain.repository.IArtistRepository
import com.example.lyricsgame.domain.repository.IGenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(api: Api): IGenreRepository =
        GenreRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideArtistRepository(api: Api): IArtistRepository =
        ArtistRepositoryImpl(api = api)

}