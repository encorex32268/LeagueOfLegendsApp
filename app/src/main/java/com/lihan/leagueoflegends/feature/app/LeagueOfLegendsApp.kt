package com.lihan.leagueoflegends.feature.app

import android.app.Application
import com.lihan.leagueoflegends.feature.champion.di.championModule
import com.lihan.leagueoflegends.feature.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LeagueOfLegendsApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LeagueOfLegendsApp)
            androidLogger(level = Level.DEBUG)
            modules(
                listOf(
                    coreModule,
                    championModule
                )
            )
        }
    }
}