package com.metra.domain.model

data class PlayerModel(
    val id: Int,
    val fullName: String,
    val position: String,
    val height: String?,
    val weight: String?,
    val team: TeamDetailsModel,
)
