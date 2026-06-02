package com.metra.domain.repository

import androidx.paging.PagingData
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamDetailsModel
import com.metra.domain.utils.Data
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for loading NBA players, player details and team details.
 */
interface NbaRepository {
    /**
     * Returns paginated list of NBA players.
     */
    fun getPlayers(): Flow<PagingData<PlayerModel>>

    /**
     * Returns player details from local cache.
     *
     * @param id player id.
     */
    suspend fun getPlayerDetails(id: Int): Data<PlayerDetailsModel>

    /**
     * Returns team details from locally saved player data.
     *
     * @param id team id.
     */
    suspend fun getTeamDetails(id: Int): Data<TeamDetailsModel>
}
