package com.lihan.leagueoflegends.feature.champion.data.repository

import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.ChampionDataDto
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampionDetailEntity
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampionInfo
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionInfo
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionDetailRepository
import com.lihan.leagueoflegends.feature.core.data.network.get
import com.lihan.leagueoflegends.feature.core.database.ChampionDetailDao
import com.lihan.leagueoflegends.feature.core.database.mapper.toRoomSkin
import com.lihan.leagueoflegends.feature.core.database.mapper.toRoomSpell
import com.lihan.leagueoflegends.feature.core.domain.DefaultSharedPreferences
import com.lihan.leagueoflegends.feature.core.domain.util.CoilImageConverters
import com.lihan.leagueoflegends.feature.core.domain.util.DataError
import com.lihan.leagueoflegends.feature.core.domain.util.Result
import com.lihan.leagueoflegends.feature.core.domain.util.map
import io.ktor.client.HttpClient

class ChampionDetailRepositoryImpl(
    private val httpClient: HttpClient,
    private val championDetailDao: ChampionDetailDao,
    private val coilImageConverters: CoilImageConverters,
    private val defaultSharedPreferences: DefaultSharedPreferences
): ChampionDetailRepository {

    override suspend fun getDetail(
        name: String
    ): Result<ChampionInfo?, DataError.Network> {
        val language = defaultSharedPreferences.getLanguage()
        val route = "https://ddragon.leagueoflegends.com/cdn/15.3.1/data/${language}/champion/${name}.json"
        val result = httpClient.get<ChampionDataDto>(
            route = route
        ).map {
            it.champions[name]?.toChampionInfo(language?:Language.US.code)
        }
        return result
    }

    override suspend fun getDetailFromRoomDatabase(name: String): ChampionInfo? {
        val language = defaultSharedPreferences.getLanguage()?:Language.US.code
        return championDetailDao.getChampionDetail(
            name = name,
            language = language
        )?.toChampionInfo(language)
    }

    override suspend fun fetchData(
        championInfo: ChampionInfo?
    ) {
        if (championInfo == null) return
        val language = defaultSharedPreferences.getLanguage()?:Language.US.code
        val iconByteArray = coilImageConverters.toByteArray(imageUrl = "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/champion/${championInfo.icon}")
        val passiveIconByteArray = coilImageConverters.toByteArray(imageUrl = "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/passive/${championInfo.passive?.image}")
        val roomSpells = championInfo.spells.mapIndexed { index, spell ->
            val image = coilImageConverters.toByteArray(
                "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/spell/${spell.image}"
            )
            spell.toRoomSpell().copy(
                id = when(index){
                    0 -> "Q"
                    1 -> "W"
                    2 -> "E"
                    else -> "R"
                },
                image = image
            )
        }
        val roomSkins = championInfo.skins.map {
            val skinImage = coilImageConverters.toByteArray(it.name)
            it.toRoomSkin().copy(
                image = skinImage
            )
        }
        val newPassive = championInfo.passive?.copy(
            imageByteArray = passiveIconByteArray
        )
        val entity = championInfo.toChampionDetailEntity(
            language = language
        ).copy(
            icon = iconByteArray,
            spells = roomSpells,
            skins = roomSkins,
            passive = newPassive
        )
        championDetailDao.upsertChampion(entity)
    }

}