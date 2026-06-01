package com.metra.data.remote.entity

import com.google.gson.annotations.SerializedName

data class TeamDetailsResponseEntity(
    @SerializedName("data")
    val data: TeamEntity,
)
