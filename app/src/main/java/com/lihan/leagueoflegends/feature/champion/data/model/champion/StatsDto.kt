package com.lihan.leagueoflegends.feature.champion.data.model.champion

import kotlinx.serialization.Serializable

@Serializable
data class StatsDto(
    val armor: Int?,
    val armorperlevel: Double?,
    val attackdamage: Int?,
    val attackdamageperlevel: Double?,
    val attackrange: Int?,
    val attackspeed: Double?,
    val attackspeedperlevel: Double?,
    val crit: Int?,
    val critperlevel: Int?,
    val hp: Int?,
    val hpperlevel: Int?,
    val hpregen: Double?,
    val hpregenperlevel: Double?,
    val movespeed: Int?,
    val mp: Int?,
    val mpperlevel: Double?,
    val mpregen: Double?,
    val mpregenperlevel: Double?,
    val spellblock: Int?,
    val spellblockperlevel: Double?
)