package com.metra.data.remote

import com.metra.data.remote.entity.PlayerResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API for loading NBA data from balldontlie API.
 */
interface NbaApi {
    /**
     * Loads NBA players using cursor-based pagination.
     *
     * @param cursor cursor for the next page. Null is used for the first page.
     * @param perPage number of players per page.
     */
    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int?,
        @Query("per_page") perPage: Int = 35,
    ): PlayerResponseEntity
}
