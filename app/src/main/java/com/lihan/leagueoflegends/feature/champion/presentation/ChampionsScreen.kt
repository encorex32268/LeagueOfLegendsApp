package com.lihan.leagueoflegends.feature.champion.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.champion.presentation.components.ChampionItem
import com.lihan.leagueoflegends.feature.champion.presentation.components.ChampionSearchBar
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi
import com.lihan.leagueoflegends.feature.core.domain.ObserveAsEvents
import com.lihan.leagueoflegends.feature.core.presentation.Translate
import com.lihan.leagueoflegends.feature.core.presentation.util.UiEvent
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionsScreenRoot(
    viewModel: ChampionsViewModel = koinViewModel(),
    onChampionClickGoToDetail: (String) -> Unit = {}
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(viewModel.uiEvent) {
        when(it){
            is UiEvent.ErrorMessage -> {
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
            }
        }
    }
    ChampionsScreen(
        state = state,
        onAction = { action ->
            when(action){
                is ChampionAction.OnChampionClick -> {
                    onChampionClickGoToDetail(action.id)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun ChampionsScreen(
    state: ChampionsState,
    onAction: (ChampionAction) -> Unit = {}
){
    if (state.isShowLanguageDialog){
        AlertDialog(
            onDismissRequest = {
                onAction(ChampionAction.CloseDialog)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAction(ChampionAction.CloseDialog)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.alert_dialog_close)
                    )
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    items(Language.entries.toList()){ language ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    onAction(
                                        ChampionAction.SelectedLanguage(language)
                                    )
                                    onAction(ChampionAction.CloseDialog)
                                },
                            text = language.code
                        )
                    }
                }
            }
        )
    }
    if (state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(36.dp)
            )
        }
    }else{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                ChampionSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    hintText = stringResource(R.string.champion_search_bar_hint),
                    text = state.userText,
                    onValueChange = {
                        onAction(ChampionAction.OnSearchTextChange(it))
                    }
                )
                IconButton(
                    onClick = {
                        onAction(
                            ChampionAction.ShowDialog
                        )
                    }
                ) {
                    Icon(
                        imageVector = Translate,
                        contentDescription = stringResource(R.string.translateicon_content_description)
                    )
                }

            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(
                    items = state.items
                ){championUI ->
                    ChampionItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        championUI = championUI,
                        onItemClick = {
                            onAction(
                                ChampionAction.OnChampionClick(it)
                            )
                        }
                    )
                }
            }

        }

    }


}

@Composable
@Preview(showSystemUi = true)
private fun ChampionsScreenPreview(){
    val itemsPreview = (0..10).map {
        ChampionUi(
            blurb = "Once honored defenders of Shurima against the Void, Aatrox " +
                    "and his brethren would eventually become an even greater " +
                    "threat to Runeterra, and were defeated only by cunning mortal " +
                    "sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
            image = "Aatrox.png",
            name = "Aatrox",
            title = "the Darkin Blade",
        )
    }
    LeagueOfLegendsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ){
            ChampionsScreen(
                state = ChampionsState(
                    isLoading = false,
                    items = itemsPreview
                ),
                onAction = {}
            )
        }
    }
}