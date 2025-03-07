package com.lihan.leagueoflegends.feature.champion.domain.repository

import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.domain.model.Champions
import com.lihan.leagueoflegends.feature.core.domain.util.DataError
import com.lihan.leagueoflegends.feature.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 *  all
 *     https://ddragon.leagueoflegends.com/cdn/15.3.1/data/en_US/champion.json
 *  get one
 *     https://ddragon.leagueoflegends.com/cdn/15.3.1/data/en_US/champion/Aatrox.json
 */

interface ChampionsRepository {

    suspend fun getAllChampions(): Result<Champions,DataError.Network>

    fun getAllChampionsFromRoom(): Flow<List<Champion>>

    suspend fun fetchData(data: List<Champion>)
}