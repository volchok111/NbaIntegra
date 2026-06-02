package com.metra.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metra.data.local.entity.PlayerLocalEntity

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPlayers(players: List<PlayerLocalEntity>)

    @Query("SELECT * FROM players WHERE id = :playerId LIMIT 1")
    suspend fun getPlayerById(playerId: Int): PlayerLocalEntity?

    @Query("SELECT * FROM players WHERE team_id = :teamId LIMIT 1")
    suspend fun getPlayerByTeamId(teamId: Int): PlayerLocalEntity?

    @Query("SELECT * FROM players ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getPlayersPage(
        limit: Int,
        offset: Int,
    ): List<PlayerLocalEntity>
}
