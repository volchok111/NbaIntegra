package com.metra.nbaintegra.feature.teamdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.metra.domain.model.TeamDetailsModel
import com.metra.nbaintegra.R
import com.metra.nbaintegra.core.ui.NbaColors
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXS
import com.metra.nbaintegra.core.ui.NbaDimensions.sizeXXS
import com.metra.nbaintegra.core.ui.NbaText
import com.metra.nbaintegra.core.ui.NbaTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetailsScreen(
    teamId: Int,
    onBackClick: () -> Unit,
    viewModel: TeamDetailsViewModel =
        koinViewModel(
            key = "team-details-$teamId",
        ),
) {
    val state = viewModel.states.collectAsStateWithLifecycle().value

    LaunchedEffect(teamId) {
        viewModel.loadTeam(teamId)
    }

    TeamDetailsScreenImpl(
        state = state,
        onBackClick = onBackClick,
    )
}

@Composable
private fun TeamDetailsScreenImpl(
    state: TeamDetailsViewModel.State,
    onBackClick: () -> Unit,
) {
    val team = state.team

    Scaffold(
        topBar = {
            NbaTopBar(
                title = team?.fullName ?: stringResource(id = R.string.team_details_title),
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

                team == null -> {
                    NbaText(
                        text = stringResource(id = R.string.team_details_not_available),
                        style = MaterialTheme.typography.bodyLarge,
                        color = NbaColors.chrome600,
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .padding(sizeS),
                    )
                }

                else -> {
                    TeamDetailsContent(
                        team = team,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(sizeXS),
                    )
                }
            }
        }
    }
}

@Composable
private fun TeamDetailsContent(
    team: TeamDetailsModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizeS),
    ) {
        TeamHeaderCard(team = team)

        DetailsInfoCard(
            title = stringResource(id = R.string.team_details_location),
            rows =
                listOf(
                    stringResource(id = R.string.team_details_city) to team.city,
                    stringResource(id = R.string.team_details_full_name) to team.fullName,
                    stringResource(id = R.string.team_details_abbreviation) to team.abbreviation,
                ),
        )

        DetailsInfoCard(
            title = stringResource(id = R.string.team_details_league_info),
            rows =
                listOf(
                    stringResource(id = R.string.team_details_conference) to team.conference,
                    stringResource(id = R.string.team_details_division) to team.division,
                ),
        )
    }
}

@Composable
private fun TeamHeaderCard(team: TeamDetailsModel) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = sizeXXS),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = NbaColors.chrome50),
    ) {
        Column(
            modifier = Modifier.padding(sizeS),
        ) {
            DetailLabel(text = stringResource(id = R.string.team_details_team))

            Spacer(modifier = Modifier.height(sizeXS))

            DetailValue(text = team.fullName)

            Spacer(modifier = Modifier.height(sizeS))

            DetailLabel(text = stringResource(id = R.string.team_details_name))

            Spacer(modifier = Modifier.height(sizeXS))

            DetailValue(text = team.name)
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
            text = value.ifBlank { "-" },
            style = MaterialTheme.typography.bodyMedium,
            color = NbaColors.black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
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
        text = text.ifBlank { "-" },
        style = MaterialTheme.typography.headlineSmall,
        color = NbaColors.black,
        fontWeight = FontWeight.Bold,
    )
}
