package com.lihan.leagueoflegends.feature.core.presentation.util

sealed interface UiEvent {
    data class ErrorMessage(
        val message: String
    ): UiEvent
}