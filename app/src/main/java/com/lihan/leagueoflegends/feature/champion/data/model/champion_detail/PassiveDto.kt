package com.lihan.leagueoflegends.feature.champion.data.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class PassiveDto(
    val name: String?,
    val description: String?,
    val image: InfoImageDto?
)
