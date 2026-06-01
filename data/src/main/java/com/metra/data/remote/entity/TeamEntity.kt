package com.metra.data.remote.entity

import com.google.gson.annotations.SerializedName

data class TeamEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("abbreviation")
    val abbreviation: String,
)
