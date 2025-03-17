package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.components.SpellItem
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.ChampionInfoUi
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model.SpellUi
import com.lihan.leagueoflegends.feature.core.domain.ObserveAsEvents
import com.lihan.leagueoflegends.feature.core.presentation.util.UiEvent
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionDetailScreenRoot(
    viewModel: ChampionDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(viewModel.uiEvent) {
        when(it){
            is UiEvent.ErrorMessage -> {
                Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    ChampionDetailScreen(
        state = state,
        onBack = onBack
    )
}

@Composable
fun ChampionDetailScreen(
    state: ChampionDetailState,
    onBack: () -> Unit = {}
) {
    var selectedImageUrl by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var selectedImageByteArray by rememberSaveable {
        mutableStateOf<ByteArray?>(null)
    }
    BackHandler(
        onBack = {
            if (selectedImageUrl != null || selectedImageByteArray!=null){
                selectedImageUrl = null
                selectedImageByteArray = null
            }else{
                onBack()
            }
        }
    )

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

        state.championInfoUI?.skins?.getOrNull(0)?.let { skinUI ->
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.3f),
                model = skinUI.imageByteArray ?: skinUI.image,
                contentDescription = "champion background",
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                SubcomposeAsyncImage(
                    modifier = Modifier.size(70.dp),
                    model = if (state.championInfoUI?.iconByteArray != null) state.championInfoUI.iconByteArray else state.championInfoUI?.icon,
                    contentDescription = "Champion Icon ${state.championInfoUI?.icon}",
                    loading = {
                        CircularProgressIndicator()
                    }
                )
                Text(
                    text = state.championInfoUI?.name?:""
                )
                Text(
                    text = state.championInfoUI?.title?:""
                )
            }
            Column {
                state.championInfoUI?.passiveSpellUi?.let {
                    SpellItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        spellUI = it
                    )
                }
                state.championInfoUI?.spells?.forEachIndexed { index, it ->
                    SpellItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        spellUI = it
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                items(state.championInfoUI?.skins?: emptyList()){ skinUI ->
                    SubcomposeAsyncImage(
                        modifier = Modifier.clickable {
                            selectedImageUrl = skinUI.image
                            selectedImageByteArray = skinUI.imageByteArray
                        },
                        model = skinUI.imageByteArray?:skinUI.image,
                        contentDescription = stringResource(R.string.skin_image, skinUI.name),
                        loading = {
                            CircularProgressIndicator()
                        }
                    )
                }
            }
        }
        if (selectedImageUrl != null || selectedImageByteArray != null){

            var scale by remember {
                mutableStateOf(1f)
            }
            var offset by remember {
                mutableStateOf(Offset.Zero)
            }


            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){

                val transState = rememberTransformableState { zoomChange, panChange, rotationChange ->
                    scale = (scale * zoomChange).coerceIn(1f, 5f)

                    val extraWidth = (scale - 1) * constraints.maxWidth
                    val extraHeight = (scale - 1) * constraints.maxHeight

                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
                    )
                }

                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = offset.x
                            translationY = offset.y
                        }
                        .transformable(transState)
                        .padding(8.dp)
                    ,
                    model = selectedImageByteArray?:selectedImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }


}

@Composable
@Preview(showSystemUi = true)
fun ChampionDetailScreenPreview(){
    val championUIPreview = ChampionInfoUi(
        id = "Zac",
        name = "Zac",
        title = "the Secret Weapon",
        icon = "",
        skins = listOf(

        ),
        spells = listOf(
            SpellUi(
                id = "ZacQ",
                name = "Stretching Strikes",
                description = "Zac stretches an arm, grabbing an enemy. Attacking a different enemy will cause him to throw both targets towards each other.",
                cooldownBurn = "14/12.5/11/9.5/8",
                image = ""
            ),
            SpellUi(
                id = "ZacW",
                name = "Unstable Matter",
                description = "Zac explodes outward towards nearby enemies, dealing a percentage of their maximum health as magic damage.",
                cooldownBurn = "5",
                image = ""
            ),
            SpellUi(
                id = "ZacE",
                name = "Elastic Slingshot",
                description = "Zac attaches his arms to the ground and stretches back, launching himself forward.",
                cooldownBurn = "22/19/16/13/10",
                image = ""
            ),
            SpellUi(
                id = "ZacR",
                name = "Let's Bounce!",
                description = "Zac bounces four times, knocking up enemies hit and slowing them.",
                cooldownBurn = "120/105/90",
                image = ""
            )
        )
    )
    LeagueOfLegendsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ChampionDetailScreen(
                state = ChampionDetailState(
                    championInfoUI = championUIPreview,
                    isLoading = false
                )
            )
        }
    }
}