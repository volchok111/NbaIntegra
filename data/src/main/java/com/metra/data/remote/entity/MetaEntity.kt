package com.metra.data.remote.entity

import com.google.gson.annotations.SerializedName

data class MetaEntity(
    @SerializedName("next_cursor")
    val nextCursor: Int?,
    @SerializedName("per_page")
    val perPage: Int,
)
