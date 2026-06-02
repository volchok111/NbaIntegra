package com.metra.data.mappers

import com.metra.data.local.entity.PlayerLocalEntity
import com.metra.data.local.entity.TeamLocalEntity
import com.metra.data.remote.entity.PlayerEntity
import com.metra.data.remote.entity.TeamEntity
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.model.PlayerModel
import com.metra.domain.model.TeamDetailsModel

fun PlayerEntity.toPlayerModel(): PlayerModel =
    PlayerModel(
        id = id,
        fullName = "$firstName $lastName",
        position = position,
        height = height,
        weight = weight,
        team = team.toTeamModel(),
    )

fun TeamEntity.toTeamModel(): TeamDetailsModel =
    TeamDetailsModel(
        id = id,
        conference = conference,
        division = division,
        city = city,
        name = name,
        fullName = fullName,
        abbreviation = abbreviation,
    )

fun PlayerEntity.toLocalPlayerEntity(): PlayerLocalEntity =
    PlayerLocalEntity(
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
        team = team.toLocalTeamEntity(),
    )

fun TeamEntity.toLocalTeamEntity(): TeamLocalEntity =
    TeamLocalEntity(
        id = id,
        conference = conference,
        division = division,
        city = city,
        name = name,
        fullName = fullName,
        abbreviation = abbreviation,
    )

fun PlayerLocalEntity.toDetailsModel(): PlayerDetailsModel =
    PlayerDetailsModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        fullName = fullName,
        position = position,
        height = height,
        weight = weight,
        jerseyNumber = jerseyNumber,
        college = college,
        country = country,
        draftYear = draftYear,
        draftRound = draftRound,
        draftNumber = draftNumber,
        team = team.toLocalTeamModel(),
    )

fun TeamLocalEntity.toLocalTeamModel(): TeamDetailsModel =
    TeamDetailsModel(
        id = id,
        conference = conference,
        division = division,
        city = city,
        name = name,
        fullName = fullName,
        abbreviation = abbreviation,
    )
