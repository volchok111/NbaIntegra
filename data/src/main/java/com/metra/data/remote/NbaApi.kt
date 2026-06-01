package com.metra.data.remote

import com.metra.data.remote.entity.PlayerDetailsResponseEntity
import com.metra.data.remote.entity.PlayerResponseEntity
import com.metra.data.remote.entity.TeamDetailsResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NbaApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int?,
        @Query("per_page") perPage: Int = 35,
    ): PlayerResponseEntity

    @GET("players/{id}")
    suspend fun getPlayerDetails(
        @Path(value = "id") id: Int,
    ): PlayerDetailsResponseEntity

    @GET("teams/{id}")
    suspend fun getTeamDetails(
        @Path(value = "id") id: Int,
    ): TeamDetailsResponseEntity
}
