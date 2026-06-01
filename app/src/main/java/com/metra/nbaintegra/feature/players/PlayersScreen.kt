package com.metra.nbaintegra.feature.players

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.metra.domain.model.PlayerModel
import com.metra.nbaintegra.R
import com.metra.nbaintegra.core.ui.NbaColors
import com.metra.nbaintegra.core.ui.NbaColors.black
import com.metra.nbaintegra.core.ui.NbaColors.chrome400
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXXS
import com.metra.nbaintegra.core.ui.NbaText
import com.metra.nbaintegra.core.ui.NbaTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerScreen(
    onPlayerClick: (Int) -> Unit,
    viewModel: PlayersViewModel = koinViewModel(),
) {
    val players = viewModel.players.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            NbaTopBar(
                title = stringResource(id = R.string.home_screen_top_bar_title),
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(NbaColors.background),
        ) {
            LazyColumn(
                modifier =
                    Modifier
                        .padding(sizeS),
            ) {
                items(
                    count = players.itemCount,
                    key = { index -> players[index]?.id ?: index },
                ) { index ->

                    val player = players[index]
                    if (player != null) {
                        ListItem(
                            modifier =
                                Modifier
                                    .clickable {
                                        onPlayerClick(player.id)
                                    },
                            player = player,
                        )
                        Spacer(modifier = Modifier.height(sizeS))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ListItem(
    modifier: Modifier = Modifier,
    player: PlayerModel,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = sizeXXS),
        modifier =
            modifier
                .fillMaxWidth()
                .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = NbaColors.chrome50),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(sizeXS),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = R.drawable.nba_player,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .clip(CircleShape)
                        .size(85.dp),
            )

            Column(
                modifier =
                    Modifier
                        .padding(start = sizeS),
            ) {
                NbaText(
                    text = player.fullName,
                    style = MaterialTheme.typography.headlineSmall,
                    color = black,
                    fontWeight = FontWeight.Bold,
                )
                NbaText(
                    text = "${stringResource(id = R.string.home_screen_team)} ${player.team.fullName}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = chrome400,
                )
            }
        }
    }
}
