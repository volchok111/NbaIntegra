package com.metra.data.remote

import com.metra.data.remote.entity.PlayerResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int?,
        @Query("per_page") perPage: Int = 35,
    ): PlayerResponseEntity
}
