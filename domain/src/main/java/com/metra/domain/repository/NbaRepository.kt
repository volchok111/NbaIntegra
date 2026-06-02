package com.metra.domain.repository

import androidx.paging.PagingData
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamDetailsModel
import com.metra.domain.utils.Data
import kotlinx.coroutines.flow.Flow

interface NbaRepository {
    fun getPlayers(): Flow<PagingData<PlayerModel>>

    suspend fun getPlayerDetails(id: Int): Data<PlayerDetailsModel>

    suspend fun getTeamDetails(id: Int): Data<TeamDetailsModel>
}
