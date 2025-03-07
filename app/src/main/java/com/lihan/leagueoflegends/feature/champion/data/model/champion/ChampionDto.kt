package com.lihan.leagueoflegends.feature.champion.data.model.champion

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDto(
    val blurb: String?,
    val id: String?,
    @SerialName("image")
    val imageDto: ImageDto?,
    @SerialName("info")
    val infoDto: InfoDto?,
    val key: String?,
    val name: String?,
    val partype: String?,
    @SerialName("stats")
    val statsDto: StatsDto?,
    val tags: List<String>?,
    val title: String?,
    val version: String?
)