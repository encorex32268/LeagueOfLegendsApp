package com.lihan.leagueoflegends.feature.champion.presentation

import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi

data class ChampionsState(
    val isLoading: Boolean = true,
    val version: String = "",
    val items: List<ChampionUi> = emptyList(),
    val userText: String = "",
    val isShowLanguageDialog: Boolean = false
)
