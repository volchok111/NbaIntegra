package com.metra.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metra.data.local.dao.PlayerDao
import com.metra.data.mappers.toDetailsModel
import com.metra.data.mappers.toLocalTeamModel
import com.metra.data.paging.PlayerPagingSource
import com.metra.data.remote.NbaApi
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamDetailsModel
import com.metra.domain.repository.NbaRepository
import com.metra.domain.utils.Data
import kotlinx.coroutines.flow.Flow

class NbaRepositoryImpl(
    private val nbaApi: NbaApi,
    private val playerDao: PlayerDao,
) : NbaRepository {
    override fun getPlayers(): Flow<PagingData<PlayerModel>> =
        Pager(
            config =
                PagingConfig(
                    pageSize = 35,
                    initialLoadSize = 35,
                    prefetchDistance = 5,
                    enablePlaceholders = false,
                ),
            pagingSourceFactory = {
                PlayerPagingSource(
                    nbaApi = nbaApi,
                    playerDao = playerDao,
                )
            },
        ).flow

    override suspend fun getPlayerDetails(id: Int): Data<PlayerDetailsModel> =
        try {
            val player = playerDao.getPlayerById(id)

            if (player == null) {
                Data.Error(
                    IllegalStateException("Player with id=$id was not found in local database"),
                )
            } else {
                Data.Success(player.toDetailsModel())
            }
        } catch (exception: Exception) {
            Data.Error(exception)
        }

    override suspend fun getTeamDetails(id: Int): Data<TeamDetailsModel> =
        try {
            val player = playerDao.getPlayerByTeamId(id)

            if (player == null) {
                Data.Error(
                    IllegalStateException("Team with id=$id was not found in local database"),
                )
            } else {
                Data.Success(player.team.toLocalTeamModel())
            }
        } catch (exception: Exception) {
            Data.Error(exception)
        }
}
