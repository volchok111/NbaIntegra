package com.metra.domain.usecase

import com.metra.domain.model.TeamDetailsModel
import com.metra.domain.repository.NbaRepository
import com.metra.domain.utils.Data
import com.metra.domain.utils.SuspendUseCase

/**
 * UС for loading team details by id.
 *
 * Team details are taken from locally saved player data.
 */
class GetTeamDetailsUseCase(
    private val repository: NbaRepository,
) : SuspendUseCase<Int, Data<TeamDetailsModel>> {
    override suspend fun invoke(input: Int): Data<TeamDetailsModel> = repository.getTeamDetails(input)
}
