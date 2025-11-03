package com.example.lyricsgame.di

import android.content.Context
import androidx.room.Room
import com.example.lyricsgame.data.local.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ScoreDatabase {
        return Room.databaseBuilder(
            context,
            ScoreDatabase::class.java,
            "exa_room_db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideScoreDao(database: ScoreDatabase) = database.scoreDao()

}