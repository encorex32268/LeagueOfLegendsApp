package com.lihan.leagueoflegends.feature.core.domain


interface DefaultSharedPreferences {
    fun saveLanguage(language: String)
    fun getLanguage(): String?
}