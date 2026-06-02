package com.metra.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.metra.data.local.dao.PlayerDao
import com.metra.data.mappers.toLocalEntity
import com.metra.data.mappers.toModel
import com.metra.data.remote.NbaApi
import com.metra.domain.model.PlayerModel

class PlayerPagingSource(
    private val nbaApi: NbaApi,
    private val playerDao: PlayerDao,
) : PagingSource<Int, PlayerModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerModel> =
        try {
            val response =
                nbaApi.getPlayers(
                    cursor = params.key,
                    perPage = PLAYERS_PAGE_SIZE,
                )

            playerDao.upsertPlayers(
                response.data.map { it.toLocalEntity() },
            )

            LoadResult.Page(
                data = response.data.map { it.toModel() },
                prevKey = null,
                nextKey = response.meta.nextCursor,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    override fun getRefreshKey(state: PagingState<Int, PlayerModel>): Int? = null

    companion object {
        private const val PLAYERS_PAGE_SIZE = 35
    }
}
