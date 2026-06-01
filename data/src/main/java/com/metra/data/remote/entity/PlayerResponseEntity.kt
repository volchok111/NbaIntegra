package com.metra.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PlayerResponseEntity(
    @SerializedName("data")
    val data: List<PlayerEntity>,
    @SerializedName("meta")
    val meta: MetaEntity,
)
