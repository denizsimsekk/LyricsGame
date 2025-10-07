package com.example.lyricsgame.di

import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.data.repository.ArtistRepositoryImpl
import com.example.lyricsgame.data.repository.GenreRepositoryImpl
import com.example.lyricsgame.data.repository.TrackRepositoryImpl
import com.example.lyricsgame.domain.repository.IArtistRepository
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.repository.ITrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(api: Api): IGenreRepository =
        GenreRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideArtistRepository(api: Api): IArtistRepository =
        ArtistRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideTrackRepository(api: Api): ITrackRepository =
        TrackRepositoryImpl(api = api)

}