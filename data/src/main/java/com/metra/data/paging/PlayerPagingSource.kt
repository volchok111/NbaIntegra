package com.metra.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.metra.data.local.dao.PlayerDao
import com.metra.data.mappers.toLocalPlayerEntity
import com.metra.data.mappers.toPlayerModel
import com.metra.data.remote.NbaApi
import com.metra.domain.model.PlayerModel

/**
 * Paging source for loading NBA players from the API.
 *
 * Loaded players are also saved locally, so player and team details
 * can be opened without extra API requests.
 */
class PlayerPagingSource(
    private val nbaApi: NbaApi,
    private val playerDao: PlayerDao,
) : PagingSource<Int, PlayerModel>() {
    /**
     * Loads one page of players.
     *
     * First tries to load data from the API. If the request is successful,
     * players are saved locally and returned to the UI.
     *
     * If the API request fails, cached players are loaded from Room.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerModel> {
        val cursor = params.key
        val offset = cursor ?: 0

        return try {
            val response =
                nbaApi.getPlayers(
                    cursor = cursor,
                    perPage = PLAYERS_PAGE_SIZE,
                )

            playerDao.upsertPlayers(
                response.data.map { player ->
                    player.toLocalPlayerEntity()
                },
            )

            LoadResult.Page(
                data =
                    response.data.map { player ->
                        player.toPlayerModel()
                    },
                prevKey = null,
                nextKey = response.meta.nextCursor,
            )
        } catch (exception: Exception) {
            loadCachedPlayers(
                exception = exception,
                offset = offset,
            )
        }
    }

    /**
     * Loads a page of players from local storage when the API request fails.
     *
     * @param exception original API or network exception.
     * @param offset local page offset.
     */
    private suspend fun loadCachedPlayers(
        exception: Exception,
        offset: Int,
    ): LoadResult<Int, PlayerModel> {
        val cachedPlayers =
            playerDao.getPlayersPage(
                limit = PLAYERS_PAGE_SIZE,
                offset = offset,
            )

        return if (cachedPlayers.isNotEmpty()) {
            LoadResult.Page(
                data =
                    cachedPlayers.map { player ->
                        player.toPlayerModel()
                    },
                prevKey = null,
                nextKey =
                    if (cachedPlayers.size < PLAYERS_PAGE_SIZE) {
                        null
                    } else {
                        offset + cachedPlayers.size
                    },
            )
        } else {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlayerModel>): Int? = null

    private companion object {
        const val PLAYERS_PAGE_SIZE = 35
    }
}
