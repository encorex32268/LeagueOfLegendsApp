package com.lihan.leagueoflegends.feature.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lihan.leagueoflegends.feature.core.database.entity.ChampionDetailEntity
import com.lihan.leagueoflegends.feature.core.database.entity.ChampionEntity

@Database(
    entities = [
        ChampionEntity::class,
        ChampionDetailEntity::class
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChampionRoomDatabase: RoomDatabase() {
    abstract val championDao: ChampionDao
    abstract val championDetailDao: ChampionDetailDao
}