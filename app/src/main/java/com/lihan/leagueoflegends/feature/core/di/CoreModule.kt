package com.lihan.leagueoflegends.feature.core.di

import androidx.room.Room
import coil.ImageLoader
import com.lihan.leagueoflegends.feature.core.data.sharedpreferences.DefaultSharedPreferencesManager
import com.lihan.leagueoflegends.feature.core.database.ChampionDao
import com.lihan.leagueoflegends.feature.core.database.ChampionDetailDao
import com.lihan.leagueoflegends.feature.core.database.ChampionRoomDatabase
import com.lihan.leagueoflegends.feature.core.data.network.HttpClientFactory
import com.lihan.leagueoflegends.feature.core.domain.DefaultSharedPreferences
import com.lihan.leagueoflegends.feature.core.domain.util.CoilImageConverters
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single{
        HttpClientFactory().build()
    }.bind<HttpClient>()

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ChampionRoomDatabase::class.java,
            name = "champions.db"
        ).build()
    }.bind<ChampionRoomDatabase>()

    single {
        get<ChampionRoomDatabase>().championDao
    }.bind<ChampionDao>()

    single {
        get<ChampionRoomDatabase>().championDetailDao
    }.bind<ChampionDetailDao>()

    single {
        CoilImageConverters(
            context = androidContext(),
            imageLoader = ImageLoader(androidContext())
        )
    }

    single {
        DefaultSharedPreferencesManager(
            context = androidContext()
        )
    }.bind<DefaultSharedPreferences>()
}