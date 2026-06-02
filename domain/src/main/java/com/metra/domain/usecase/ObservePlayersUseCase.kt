package com.metra.domain.usecase

import androidx.paging.PagingData
import com.metra.domain.model.PlayerModel
import com.metra.domain.repository.NbaRepository
import com.metra.domain.utils.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

/**
 * UС for observing paginated NBA players.
 *
 * The returned flow is used by the players list screen.
 */
class ObservePlayersUseCase(
    private val repository: NbaRepository,
) : SynchronousUseCase<Unit, Flow<PagingData<PlayerModel>>> {
    override fun invoke(input: Unit): Flow<PagingData<PlayerModel>> = repository.getPlayers()
}
