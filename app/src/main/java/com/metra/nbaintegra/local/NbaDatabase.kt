package com.metra.nbaintegra.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.metra.data.local.dao.PlayerDao
import com.metra.data.local.entity.PlayerLocalEntity

@Database(
    entities = [PlayerLocalEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class NbaDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}
