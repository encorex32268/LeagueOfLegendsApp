package com.lihan.leagueoflegends.feature.champion.di

import androidx.lifecycle.SavedStateHandle
import com.lihan.leagueoflegends.feature.champion.data.repository.ChampionDetailRepositoryImpl
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionDetailRepository
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.ChampionDetailViewModel
import com.lihan.leagueoflegends.feature.champion.data.repository.ChampionsRepositoryImpl
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionsRepository
import com.lihan.leagueoflegends.feature.champion.presentation.ChampionsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val championModule = module {

    singleOf(::ChampionDetailRepositoryImpl).bind<ChampionDetailRepository>()
    viewModel { (handle: SavedStateHandle) ->
        ChampionDetailViewModel(
            savedStateHandle = handle,
            repository = get()
        )
    }

    singleOf(::ChampionsRepositoryImpl).bind<ChampionsRepository>()
    viewModelOf(::ChampionsViewModel)
}