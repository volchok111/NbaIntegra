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

@Composable
fun NbaNavHost(modifier: Modifier = Modifier) {
    val activity = LocalActivity.current

    val backStack = rememberNavBackStack(PlayerRoute)

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
                entry<PlayerRoute> {
                    PlayerScreen(
                        onPlayerClick = { playerId ->
                            backStack.add(
                                PlayerDetailRoute(playerId = playerId),
                            )
                        },
                    )
                }

                entry<PlayerDetailRoute> { route ->
                    PlayerDetailsScreen(
                        playerId = route.playerId,
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        onTeamClick = { teamId ->
//                        backStack.add(
//                            TeamDetailRoute(teamId = teamId)
//                        )
                        },
                    )
                }

//            entry<TeamDetailRoute> { route ->
//                TeamDetailScreen(
//                    teamId = route.teamId,
//                    onBackClick = {
//                        backStack.removeLastOrNull()
//                    },
//                )
//            }
            },
    )
}
