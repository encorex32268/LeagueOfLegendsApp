package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.mapper

import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionInfo
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.ChampionInfoUi
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.SkinUi
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.SpellUi

fun ChampionInfo.toChampionInfoUI(): ChampionInfoUi {
    val iconUrl = "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/champion/${icon}"
    return ChampionInfoUi(
        id = id,
        name = name,
        title = title,
        spells = spells.map {
            it.toSpellUI()
                            },
        icon = iconUrl,
        skins = skins.map {
           it.toSkinUI(
               championName = id
           )
        },
        iconByteArray = iconByteArray,
        passiveSpellUi = passive?.toSpellUI()

    )
}

fun Spell.toSpellUI(): SpellUi {
    val spellImage = "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/spell/${image}"
    return SpellUi(
        id = id,
        name = name,
        description = description,
        cooldownBurn = cooldownBurn,
        image = spellImage,
        imageByteArray = imageByteArray
    )
}

fun Skin.toSkinUI(championName: String): SkinUi {
    val skinImage = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${championName}_${num}.jpg"
    return SkinUi(
        num = num,
        name = name,
        image = skinImage,
        imageByteArray = image

    )
}

fun Passive.toSpellUI(): SpellUi {
    val passiveImage = "https://ddragon.leagueoflegends.com/cdn/15.3.1/img/passive/${this.image}"
    return SpellUi(
        name = name?:"",
        description = description?:"",
        image = passiveImage,
        imageByteArray = imageByteArray
    )
}

