package com.lihan.leagueoflegends.feature.champion.presentation

import com.lihan.leagueoflegends.feature.champion.domain.model.Language

sealed interface ChampionAction {
    data class OnChampionClick(
        val id: String
    ): ChampionAction

    data class OnSearchTextChange(
        val text: String
    ): ChampionAction

    data class SelectedLanguage(
        val language: Language
    ): ChampionAction

    data object ShowDialog: ChampionAction
    data object CloseDialog: ChampionAction
}