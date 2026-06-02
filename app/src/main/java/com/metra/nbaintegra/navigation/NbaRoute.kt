package com.metra.nbaintegra.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NbaRoute : NavKey

@Serializable
data object PlayerRoute : NbaRoute

@Serializable
data class PlayerDetailsRoute(
    val playerId: Int,
) : NbaRoute

@Serializable
data class TeamDetailsRoute(
    val teamId: Int,
) : NbaRoute
