package com.metra.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PlayerDetailsResponseEntity(
    @SerializedName("data")
    val data: PlayerEntity,
)
