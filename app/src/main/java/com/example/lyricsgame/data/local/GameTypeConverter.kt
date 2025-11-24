package com.example.lyricsgame.data.local

import androidx.room.TypeConverter
import com.example.lyricsgame.domain.viewentity.GameType

class GameTypeConverter {

    @TypeConverter
    fun fromGameType(value: GameType?): String? = value?.name

    @TypeConverter
    fun toGameType(value: String?): GameType? = value?.let { GameType.valueOf(it) }
}