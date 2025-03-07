package com.lihan.leagueoflegends.feature.champion.data.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class ChampionInfoDto(
    val id: String?,
    val name: String?,
    val title: String?,
    val image: InfoImageDto?,
    val skins: List<SkinDto>?,
    val lore: String?,
    val blurb: String?,
    val allytips: List<String>?,
    val enemytips: List<String>?,
    val tags: List<String>?,
    val spells: List<SpellDto>?,
    val passive: PassiveDto?
)
