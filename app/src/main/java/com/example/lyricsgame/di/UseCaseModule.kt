package com.example.lyricsgame.di

import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.usecase.GetGenreListUseCase
import com.example.lyricsgame.domain.usecase.GetTopTrackListByGenreUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    fun provideGetGenreListUseCase(genreRepository: IGenreRepository): GetGenreListUseCase =
        GetGenreListUseCase(genreRepository)

    fun provideGetTopTrackListByGenreUseCase(genreRepository: IGenreRepository): GetTopTrackListByGenreUseCase =
        GetTopTrackListByGenreUseCase(genreRepository)

}