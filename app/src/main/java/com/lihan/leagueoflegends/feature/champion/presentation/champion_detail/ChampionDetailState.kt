package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail

import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.ChampionInfoUi

data class ChampionDetailState(
    val championInfoUI: ChampionInfoUi?=null,
    val isLoading: Boolean = true
)
