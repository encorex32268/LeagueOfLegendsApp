package com.lihan.leagueoflegends.feature.core.data.sharedpreferences

import android.content.Context
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.core.domain.DefaultSharedPreferences

class DefaultSharedPreferencesManager(
    private val context: Context
) : DefaultSharedPreferences {

    companion object{
        private const val SHARED_PREFERENCE_NAME = "shared_preference"
        private const val LANGUAGE_KEY = "language"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
    }

    override fun saveLanguage(language: String) {
        sharedPreferences
            .edit()
            .putString(LANGUAGE_KEY,language)
            .apply()
    }

    override fun getLanguage(): String? {
        return sharedPreferences.getString(LANGUAGE_KEY,Language.US.code)
    }
}