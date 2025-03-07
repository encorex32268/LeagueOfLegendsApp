package com.lihan.leagueoflegends.feature.champion.domain.mapper

import com.lihan.leagueoflegends.feature.champion.data.model.champion.ChampionDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion.ChampionsDto
import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.domain.model.Champions
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi
import com.lihan.leagueoflegends.feature.core.database.entity.ChampionEntity

fun ChampionsDto.toChampions(): Champions {
    return Champions(
        champions = champions.map {
            it.value.toChampion()
        }
    )
}

fun ChampionDto.toChampion(): Champion {
    return Champion(
        blurb = blurb?:"",
        image = imageDto?.full?:"",
        name = name?:"",
        title = title?:"",
        id = id?:""
    )
}
fun ChampionEntity.toChampion(): Champion {
    return Champion(
        blurb = blurb,
        image = "",
        name = name,
        title = title,
        imageByteArray = image,
        id = id
    )
}
fun Champion.toChampionEntity(language: String): ChampionEntity {
    return ChampionEntity(
        name = name,
        title = title,
        blurb = blurb,
        language = language,
        image = null,
        id = id
    )
}
fun Champion.toChampionUI(): ChampionUi {
    val imageUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${id}_0.jpg"
    return ChampionUi(
        id = id,
        blurb = blurb,
        image = imageUrl,
        name = name,
        title = title,
        imageByteArray = imageByteArray
    )
}


