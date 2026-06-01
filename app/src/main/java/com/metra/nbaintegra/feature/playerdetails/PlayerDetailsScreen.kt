package com.metra.nbaintegra.feature.playerdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.metra.nbaintegra.R
import com.metra.nbaintegra.core.ui.NbaColors
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXXS
import com.metra.nbaintegra.core.ui.NbaText
import com.metra.nbaintegra.core.ui.NbaTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerDetailsScreen(
    playerId: Int,
    onBackClick: () -> Unit,
    onTeamClick: (Int) -> Unit,
    viewModel: PlayerDetailsViewModel =
        koinViewModel(
            key = "player-details-$playerId",
        ),
) {
    val state = viewModel.states.collectAsStateWithLifecycle().value

    LaunchedEffect(playerId) {
        viewModel.onLoadPlayer(playerId)
    }

    PlayerDetailsScreenImpl(
        state = state,
        onBackClick = onBackClick,
        onTeamClick = onTeamClick,
    )
}

@Composable
private fun PlayerDetailsScreenImpl(
    state: PlayerDetailsViewModel.State,
    onBackClick: () -> Unit,
    onTeamClick: (Int) -> Unit,
) {
    val player = state.player

    Scaffold(
        topBar = {
            NbaTopBar(
                title = player?.fullName ?: stringResource(id = R.string.player_details_title),
                onBackClick = onBackClick,
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
            when {
                state.loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = NbaColors.black,
                    )
                }

                player == null -> {
                    NbaText(
                        text = stringResource(id = R.string.player_details_not_available),
                        style = MaterialTheme.typography.bodyLarge,
                        color = NbaColors.chrome600,
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .padding(sizeS),
                    )
                }

                else -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(sizeXS),
                    ) {
                        PlayerMainInfoCard(
                            fullName = player.fullName,
                            position = player.position,
                        )

                        Spacer(modifier = Modifier.height(sizeS))

                        DetailsInfoCard(
                            title = stringResource(id = R.string.details_screen_physical_info),
                            rows =
                                listOf(
                                    stringResource(id = R.string.details_screen_height) to player.height.orDash(),
                                    stringResource(id = R.string.details_screen_weight) to player.weight.orDash(),
                                    stringResource(id = R.string.details_screen_jersey_number) to player.jerseyNumber.orDash(),
                                ),
                        )

                        Spacer(modifier = Modifier.height(sizeS))

                        DetailsInfoCard(
                            title = stringResource(id = R.string.details_screen_background),
                            rows =
                                listOf(
                                    stringResource(id = R.string.details_screen_college) to player.college.orDash(),
                                    stringResource(id = R.string.details_screen_country) to player.country.orDash(),
                                ),
                        )

                        Spacer(modifier = Modifier.height(sizeS))

                        DetailsInfoCard(
                            title = stringResource(id = R.string.details_screen_draft),
                            rows =
                                listOf(
                                    stringResource(id = R.string.details_screen_draft_year) to player.draftYear?.toString().orDash(),
                                    stringResource(id = R.string.details_screen_draft_round) to player.draftRound?.toString().orDash(),
                                    stringResource(id = R.string.details_screen_draft_number) to player.draftNumber?.toString().orDash(),
                                ),
                        )

                        Spacer(modifier = Modifier.height(sizeS))

                        TeamCard(
                            teamName = player.team.fullName,
                            onClick = {
                                onTeamClick(player.team.id)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerMainInfoCard(
    fullName: String,
    position: String,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = sizeXXS),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = NbaColors.chrome50),
    ) {
        Row(
            modifier = Modifier.padding(sizeS),
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier =
                    Modifier
                        .size(150.dp)
                        .padding(end = sizeS),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nba_player),
                    contentDescription = "Player Image",
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = sizeXS),
            ) {
                DetailLabel(text = stringResource(id = R.string.details_screen_name))

                Spacer(modifier = Modifier.height(sizeS))

                DetailValue(text = fullName)

                Spacer(modifier = Modifier.height(sizeS))

                DetailLabel(text = stringResource(id = R.string.details_screen_position))

                Spacer(modifier = Modifier.height(sizeS))

                DetailValue(text = position.ifBlank { "-" })
            }
        }
    }
}

@Composable
private fun DetailsInfoCard(
    title: String,
    rows: List<Pair<String, String>>,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = sizeXXS),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = NbaColors.chrome50),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(sizeS),
        ) {
            DetailLabel(text = title)

            Spacer(modifier = Modifier.height(sizeXS))

            rows.forEachIndexed { index, row ->
                DetailRow(
                    label = row.first,
                    value = row.second,
                )

                if (index != rows.lastIndex) {
                    Spacer(modifier = Modifier.height(sizeXS))
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        NbaText(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = NbaColors.chrome600,
            fontSize = 16.sp,
        )

        NbaText(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = NbaColors.black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun TeamCard(
    teamName: String,
    onClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = sizeXXS),
        modifier =
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = NbaColors.chrome50),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(sizeS),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                DetailLabel(text = stringResource(id = R.string.details_screen_team))

                Spacer(modifier = Modifier.height(sizeXS))

                DetailValue(text = teamName)
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Team details",
                modifier = Modifier.size(28.dp),
                tint = NbaColors.black,
            )
        }
    }
}

@Composable
private fun DetailLabel(text: String) {
    NbaText(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = NbaColors.chrome600,
        fontSize = 20.sp,
    )
}

@Composable
private fun DetailValue(text: String) {
    NbaText(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        color = NbaColors.black,
        fontWeight = FontWeight.Bold,
    )
}

private fun String?.orDash(): String = if (isNullOrBlank()) "-" else this
