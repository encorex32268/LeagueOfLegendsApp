package com.lihan.leagueoflegends

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.ChampionDetailScreenRoot
import com.lihan.leagueoflegends.feature.champion.presentation.ChampionsScreenRoot
import com.lihan.leagueoflegends.feature.core.domain.navigation.Routes
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                LeagueOfLegendsTheme {
                    val navController = rememberNavController()
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = innerPadding.calculateTopPadding()),
                            navController = navController,
                            startDestination = Routes.Champions
                        ){
                            composable<Routes.Champions>{
                                ChampionsScreenRoot(
                                    onChampionClickGoToDetail = {
                                        navController.navigate(
                                            Routes.ChampionDetail(it)
                                        )
                                    }
                                )
                            }
                            composable<Routes.ChampionDetail>{
                                ChampionDetailScreenRoot()
                            }

                        }
                    }

                }
            }
        }
    }
}