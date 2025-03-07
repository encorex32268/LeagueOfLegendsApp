package com.lihan.leagueoflegends.feature.champion.data.model.champion

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val attack: Int?,
    val defense: Int?,
    val difficulty: Int?,
    val magic: Int?
)