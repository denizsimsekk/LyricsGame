package com.example.lyricsgame.di

import com.example.lyricsgame.domain.repository.IAIRepository
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.repository.ITrackRepository
import com.example.lyricsgame.domain.usecase.GetGenreListUseCase
import com.example.lyricsgame.domain.usecase.track.GetTopTrackListByGenreUseCase
import com.example.lyricsgame.domain.usecase.track.GetTrackDetailUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    fun provideGetGenreListUseCase(genreRepository: IGenreRepository): GetGenreListUseCase =
        GetGenreListUseCase(genreRepository)

    fun provideGetTopTrackListByGenreUseCase(genreRepository: IGenreRepository, aiRepository: IAIRepository): GetTopTrackListByGenreUseCase =
        GetTopTrackListByGenreUseCase(genreRepository)

    fun provideGetTrackDetailUseCase(trackRepository: ITrackRepository): GetTrackDetailUseCase =
        GetTrackDetailUseCase(trackRepository)

}