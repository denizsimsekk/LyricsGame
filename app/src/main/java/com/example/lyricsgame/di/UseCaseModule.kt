package com.example.lyricsgame.di

import com.example.lyricsgame.domain.repository.IAIRepository
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.repository.ITrackRepository
import com.example.lyricsgame.domain.usecase.ai.GetAIResponseUseCase
import com.example.lyricsgame.domain.usecase.genre.GetGenreListUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartAlbumListUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartArtistsUseCase
import com.example.lyricsgame.domain.usecase.globalchart.GetGlobalChartTrackListUseCase
import com.example.lyricsgame.domain.usecase.track.GetTopTrackListByGenreUseCase
import com.example.lyricsgame.domain.usecase.track.GetTrackDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetGenreListUseCase(genreRepository: IGenreRepository): GetGenreListUseCase =
        GetGenreListUseCase(genreRepository)

    @Singleton
    @Provides
    fun provideGetTopTrackListByGenreUseCase(genreRepository: IGenreRepository, aiRepository: IAIRepository): GetTopTrackListByGenreUseCase =
        GetTopTrackListByGenreUseCase(genreRepository)

    @Singleton
    @Provides
    fun provideGetTrackDetailUseCase(trackRepository: ITrackRepository): GetTrackDetailUseCase =
        GetTrackDetailUseCase(trackRepository)

    @Singleton
    @Provides
    fun provideGetGlobalChartUseCase(globalChartRepository: IGlobalChartRepository): GetGlobalChartArtistsUseCase =
        GetGlobalChartArtistsUseCase(globalChartRepository)

    @Singleton
    @Provides
    fun provideGetGlobalChartAlbumListUseCase(globalChartRepository: IGlobalChartRepository): GetGlobalChartAlbumListUseCase =
        GetGlobalChartAlbumListUseCase(globalChartRepository)

    @Singleton
    @Provides
    fun provideGetGlobalChartTrackListUseCase(globalChartRepository: IGlobalChartRepository): GetGlobalChartTrackListUseCase =
        GetGlobalChartTrackListUseCase(globalChartRepository)

    @Singleton
    @Provides
    fun provideGetAIResponseUseCase(aiRepository: IAIRepository): GetAIResponseUseCase =
        GetAIResponseUseCase(aiRepository)

}