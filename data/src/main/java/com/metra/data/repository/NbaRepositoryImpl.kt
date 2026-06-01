package com.metra.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metra.data.mappers.toModel
import com.metra.data.paging.PlayerPagingSource
import com.metra.data.remote.NbaApi
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamModel
import com.metra.domain.repository.NbaRepository
import com.metra.domain.utils.Data
import kotlinx.coroutines.flow.Flow

class NbaRepositoryImpl(
    private val nbaApi: NbaApi,
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
                PlayerPagingSource(nbaApi)
            },
        ).flow

    override suspend fun getPlayerDetails(id: Int): Data<PlayerModel> =
        try {
            val result = nbaApi.getPlayerDetails(id).toModel()
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(ex)
        }

    override suspend fun getTeamDetails(id: Int): Data<TeamModel> =
        try {
            val result = nbaApi.getTeamDetails(id).toModel()
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(ex)
        }
}
