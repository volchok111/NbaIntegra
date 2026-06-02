package com.metra.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metra.data.local.entity.PlayerLocalEntity

/**
 * DAO for working with locally saved NBA players.
 */
@Dao
interface PlayerDao {
    /**
     * Inserts or updates loaded players.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPlayers(players: List<PlayerLocalEntity>)

    /**
     * Returns locally saved player by id.
     */
    @Query("SELECT * FROM players WHERE id = :playerId LIMIT 1")
    suspend fun getPlayerById(playerId: Int): PlayerLocalEntity?

    /**
     * Returns any locally saved player from the selected team.
     *
     * The embedded team data is used for the team details screen.
     */
    @Query("SELECT * FROM players WHERE team_id = :teamId LIMIT 1")
    suspend fun getPlayerByTeamId(teamId: Int): PlayerLocalEntity?

    /**
     * Returns one page of locally saved players.
     *
     * Used as a fallback when API loading fails.
     */
    @Query("SELECT * FROM players ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getPlayersPage(
        limit: Int,
        offset: Int,
    ): List<PlayerLocalEntity>
}
