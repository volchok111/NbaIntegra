package com.metra.domain.usecase

import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.repository.NbaRepository
import com.metra.domain.utils.Data
import com.metra.domain.utils.SuspendUseCase

/**
 * UС for loading player details by id.
 *
 * Player details are taken from locally saved data.
 */
class GetPlayerDetailsUseCase(
    private val repository: NbaRepository,
) : SuspendUseCase<Int, Data<PlayerDetailsModel>> {
    override suspend fun invoke(input: Int): Data<PlayerDetailsModel> = repository.getPlayerDetails(input)
}
