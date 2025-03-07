package com.lihan.leagueoflegends.feature.champion.data.repository

import com.lihan.leagueoflegends.feature.champion.data.model.champion.ChampionsDto
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampion
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampionEntity
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampions
import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.domain.model.Champions
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionsRepository
import com.lihan.leagueoflegends.feature.core.database.ChampionDao
import com.lihan.leagueoflegends.feature.core.data.network.get
import com.lihan.leagueoflegends.feature.core.domain.DefaultSharedPreferences
import com.lihan.leagueoflegends.feature.core.domain.util.CoilImageConverters
import com.lihan.leagueoflegends.feature.core.domain.util.DataError
import com.lihan.leagueoflegends.feature.core.domain.util.Result
import com.lihan.leagueoflegends.feature.core.domain.util.map
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChampionsRepositoryImpl(
    private val httpClient: HttpClient,
    private val championDao: ChampionDao,
    private val coilImageConverters: CoilImageConverters,
    private val defaultSharedPreferences: DefaultSharedPreferences
): ChampionsRepository {

    override suspend fun getAllChampions(): Result<Champions, DataError.Network> {
        val language = defaultSharedPreferences.getLanguage()
        val result = httpClient.get<ChampionsDto>(
            route = "https://ddragon.leagueoflegends.com/cdn/15.3.1/data/${language}/champion.json"
        ).map {
            it.toChampions()
        }
        return result
    }

    override fun getAllChampionsFromRoom(): Flow<List<Champion>> {
        val language = defaultSharedPreferences.getLanguage()?:Language.US.code
        return championDao.getChampions(language).map {
            it.map { entity ->
                entity.toChampion()
            }
        }
    }

    override suspend fun fetchData(data: List<Champion>) {
        val language = defaultSharedPreferences.getLanguage()?:Language.US.code
        val preSaveData = data.map {
            val imageUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${it.id}_0.jpg"
            val imageByteArray = coilImageConverters.toByteArray(imageUrl)
            it.toChampionEntity(language).copy(
                image = imageByteArray
            )
        }
        championDao.upsertChampion(preSaveData)
    }
}