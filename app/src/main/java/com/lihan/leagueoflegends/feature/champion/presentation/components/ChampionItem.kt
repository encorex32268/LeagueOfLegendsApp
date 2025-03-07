package com.lihan.leagueoflegends.feature.champion.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme

@Composable
fun ChampionItem(
    modifier: Modifier = Modifier,
    championUI: ChampionUi,
    onItemClick: (String) -> Unit = {}
) {

    Row(
        modifier = modifier.clickable {
            championUI.id?.let {
                onItemClick(it)
            }
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){

        SubcomposeAsyncImage(
            model = championUI.imageByteArray ?: championUI.image,
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = stringResource(R.string.championItemImage)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .alignByBaseline()
        ){
            Text(
                text = championUI.name?:"Loading..." ,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = championUI.title?:"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = championUI.blurb?:"",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ChampionItemPreview() {
    val championUI = ChampionUi(
        blurb = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
        image =  "Aatrox.png",
        name = "Aatrox",
        title = "the Darkin Blade",
    )
    LeagueOfLegendsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ChampionItem(
                championUI = championUI
            )
        }
    }
}