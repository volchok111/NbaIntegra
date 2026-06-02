package com.metra.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerLocalEntity(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val position: String,
    val height: String?,
    val weight: String?,
    val jerseyNumber: String?,
    val college: String?,
    val country: String?,
    val draftYear: Int?,
    val draftRound: Int?,
    val draftNumber: Int?,
    @Embedded(prefix = "team_")
    val team: TeamLocalEntity,
)

data class TeamLocalEntity(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val fullName: String,
    val abbreviation: String,
)
