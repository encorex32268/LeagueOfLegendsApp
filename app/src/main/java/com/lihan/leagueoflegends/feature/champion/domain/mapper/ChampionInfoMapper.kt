package com.lihan.leagueoflegends.feature.champion.domain.mapper

import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.ChampionInfoDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.PassiveDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.SkinDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.SpellDto
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionInfo
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell
import com.lihan.leagueoflegends.feature.core.database.entity.ChampionDetailEntity
import com.lihan.leagueoflegends.feature.core.database.mapper.toSkin
import com.lihan.leagueoflegends.feature.core.database.mapper.toSpell

fun ChampionInfoDto.toChampionInfo(language: String): ChampionInfo {
    return ChampionInfo(
        id = id?:"",
        name = name?:"",
        title = title?:"",
        icon = image?.full?:"",
        skins = skins?.map { it.toSkin() }?: emptyList(),
        spells = spells?.mapIndexed { index, spellDto ->
            spellDto.toSpell(index)
        }?: emptyList(),
        language = language,
        passive = passive?.toPassive()
    )
}

fun SkinDto.toSkin(): Skin {
    return Skin(
        num = num?:0,
        name = name?:""
    )
}

fun SpellDto.toSpell(index: Int): Spell {
    val newId =  when(index){
        0 -> "Q"
        1 -> "W"
        2 -> "E"
        3 -> "R"
        else -> ""
    }
    return Spell(
        id = newId,
        name = name?:"",
        description = description?:"",
        cooldownBurn = cooldownBurn?:"",
        image = image?.full?:""
    )
}

fun PassiveDto.toPassive(): Passive {
    return Passive(
        name = name,
        description = description,
        image = image?.full?:""
    )
}

fun ChampionDetailEntity.toChampionInfo(
    language: String
): ChampionInfo {
    return ChampionInfo(
        id = id,
        name = name,
        title = title,
        iconByteArray = icon,
        skins = skins.map {
            it.toSkin()
        },
        spells = spells.map {
            it.toSpell()
        },
        language = language,
        passive = passive
    )
}

fun ChampionInfo.toChampionDetailEntity(language: String): ChampionDetailEntity {
    return ChampionDetailEntity(
        name = name,
        title = title,
        icon = null,
        spells = emptyList(),
        skins = emptyList(),
        language = language,
        id = id,
        passive = passive
    )
}