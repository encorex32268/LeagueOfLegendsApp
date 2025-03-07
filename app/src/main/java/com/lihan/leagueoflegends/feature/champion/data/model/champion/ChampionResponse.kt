package com.lihan.leagueoflegends.feature.champion.data.model.champion

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionsDto(
    val version: String = "",
    @SerialName("data")
    val champions: Map<String , ChampionDto> = emptyMap()
)
