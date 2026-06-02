package com.metra.nbaintegra.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.metra.nbaintegra.feature.playerdetails.PlayerDetailsScreen
import com.metra.nbaintegra.feature.players.PlayerScreen
import com.metra.nbaintegra.feature.splash.SplashScreen
import com.metra.nbaintegra.feature.teamdetails.TeamDetailsScreen

@Composable
fun NbaNavHost(modifier: Modifier = Modifier) {
    val activity = LocalActivity.current

    val backStack = rememberNavBackStack(SplashRoute)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) backStack.removeLastOrNull() else activity?.finish()
        },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<SplashRoute> {
                    SplashScreen(
                        onSplashFinished = {
                            backStack.clear()
                            backStack.add(PlayerRoute)
                        },
                    )
                }

                entry<PlayerRoute> {
                    PlayerScreen(
                        onPlayerClick = { playerId ->
                            backStack.add(
                                PlayerDetailsRoute(playerId = playerId),
                            )
                        },
                    )
                }

                entry<PlayerDetailsRoute> { route ->
                    PlayerDetailsScreen(
                        playerId = route.playerId,
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        onTeamClick = { teamId ->
                            backStack.add(
                                TeamDetailsRoute(teamId = teamId),
                            )
                        },
                    )
                }

                entry<TeamDetailsRoute> { route ->
                    TeamDetailsScreen(
                        teamId = route.teamId,
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                    )
                }
            },
    )
}
