package com.example.lyricsgame.di

import com.example.lyricsgame.data.local.ScoreDao
import com.example.lyricsgame.data.remote.Api
import com.example.lyricsgame.data.repository.AIRepositoryImpl
import com.example.lyricsgame.data.repository.ArtistRepositoryImpl
import com.example.lyricsgame.data.repository.GenreRepositoryImpl
import com.example.lyricsgame.data.repository.GlobalChartRepositoryImpl
import com.example.lyricsgame.data.repository.ScoreRepositoryImpl
import com.example.lyricsgame.data.repository.SearchRepositoryImpl
import com.example.lyricsgame.domain.repository.IAIRepository
import com.example.lyricsgame.domain.repository.IArtistRepository
import com.example.lyricsgame.domain.repository.IGenreRepository
import com.example.lyricsgame.domain.repository.IGlobalChartRepository
import com.example.lyricsgame.domain.repository.IScoreRepository
import com.example.lyricsgame.domain.repository.ISearchRepository
import com.google.firebase.ai.GenerativeModel
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
    fun provideAIRepository(model: GenerativeModel): IAIRepository =
        AIRepositoryImpl(model)

    @Provides
    @Singleton
    fun provideScoreRepository(scoreDao: ScoreDao): IScoreRepository = ScoreRepositoryImpl(scoreDao)


    @Provides
    @Singleton
    fun provideGlobalChartRepository(api: Api): IGlobalChartRepository = GlobalChartRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideSearchRepository(api: Api): ISearchRepository = SearchRepositoryImpl(api)

}