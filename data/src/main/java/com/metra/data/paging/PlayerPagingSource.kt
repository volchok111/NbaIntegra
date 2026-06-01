package com.metra.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.metra.data.mappers.toModel
import com.metra.data.remote.NbaApi
import com.metra.domain.model.PlayerModel

class PlayerPagingSource(
    private val nbaApi: NbaApi,
) : PagingSource<Int, PlayerModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerModel> =
        try {
            val cursor = params.key

            val response =
                nbaApi.getPlayers(
                    cursor = cursor,
                    perPage = PLAYERS_PAGE_SIZE,
                )

            LoadResult.Page(
                data = response.data.map { it.toModel() },
                prevKey = null,
                nextKey = response.meta.nextCursor,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    override fun getRefreshKey(state: PagingState<Int, PlayerModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }

    companion object {
        private const val PLAYERS_PAGE_SIZE = 35
    }
}
