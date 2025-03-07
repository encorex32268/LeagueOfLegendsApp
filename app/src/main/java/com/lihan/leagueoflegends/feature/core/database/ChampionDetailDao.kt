package com.lihan.leagueoflegends.feature.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lihan.leagueoflegends.feature.core.database.entity.ChampionDetailEntity

@Dao
interface ChampionDetailDao {

    @Query("SELECT * from championdetailentity where name=:name AND language=:language" )
    suspend fun getChampionDetail(
        name: String,
        language: String
    ): ChampionDetailEntity?

    @Upsert
    suspend fun upsertChampion(championDetail: ChampionDetailEntity)
}