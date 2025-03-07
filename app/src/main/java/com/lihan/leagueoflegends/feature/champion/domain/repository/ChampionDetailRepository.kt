package com.lihan.leagueoflegends.feature.champion.domain.repository

import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionInfo
import com.lihan.leagueoflegends.feature.core.domain.util.DataError
import com.lihan.leagueoflegends.feature.core.domain.util.Result

interface ChampionDetailRepository {

    suspend fun getDetail(name: String): Result<ChampionInfo?, DataError.Network>

    suspend fun getDetailFromRoomDatabase(name: String): ChampionInfo?

    suspend fun fetchData(championInfo: ChampionInfo?)
}