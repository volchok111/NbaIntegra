package com.metra.data.mappers

import com.metra.data.remote.entity.PlayerEntity
import com.metra.data.remote.entity.TeamEntity
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamModel

fun PlayerEntity.toModel(): PlayerModel =
    PlayerModel(
        id = id,
        fullName = "$firstName $lastName",
        position = position,
        height = height,
        weight = weight,
        team = team.toTeamModel(),
    )

fun PlayerEntity.toDetailsModel(): PlayerDetailsModel =
    PlayerDetailsModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        fullName = "$firstName $lastName",
        position = position,
        height = height,
        weight = weight,
        jerseyNumber = jerseyNumber,
        college = college,
        country = country,
        draftYear = draftYear,
        draftRound = draftRound,
        draftNumber = draftNumber,
        team = team.toTeamModel(),
    )

fun TeamEntity.toTeamModel(): TeamModel =
    TeamModel(
        id = id,
        conference = conference,
        division = division,
        city = city,
        name = name,
        fullName = fullName,
        abbreviation = abbreviation,
    )
