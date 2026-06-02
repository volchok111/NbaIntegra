package com.metra.nbaintegra.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.metra.data.local.dao.PlayerDao
import com.metra.data.local.entity.PlayerLocalEntity

/**
 * Room database for storing loaded NBA players.
 *
 * Saved players are used for player details, team details,
 * and simple cached list fallback when the API is unavailable.
 */
@Database(
    entities = [PlayerLocalEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class NbaDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}
