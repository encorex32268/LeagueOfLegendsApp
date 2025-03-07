package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.SpellUi

@Composable
fun SpellItem(
    modifier: Modifier = Modifier,
    spellUI: SpellUi
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            modifier = Modifier.widthIn(24.dp,50.dp),
            text = spellUI.id,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        SubcomposeAsyncImage(
            modifier = Modifier.size(48.dp),
            model = spellUI.imageByteArray ?: spellUI.image,
            contentDescription = "Spell ${spellUI.id}",
            loading = {
                CircularProgressIndicator()
            }
        )
        Column {
            Text(
                text = spellUI.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = spellUI.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            if (spellUI.cooldownBurn.isNotEmpty()){
                Text(
                    text = "CD:${spellUI.cooldownBurn}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

    }


}