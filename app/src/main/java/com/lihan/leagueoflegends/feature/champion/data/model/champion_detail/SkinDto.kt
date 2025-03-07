package com.lihan.leagueoflegends.feature.champion.data.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class SkinDto(
    val num: Int?,
    val name: String?
)
