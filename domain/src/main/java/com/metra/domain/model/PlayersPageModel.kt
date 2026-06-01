package com.metra.domain.model

data class PlayersPageModel(
    val players: List<PlayerModel>,
    val nextCursor: Int?,
)
