package com.metra.nbaintegra.feature.players

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.metra.domain.usecase.ObservePlayersUseCase
import com.metra.domain.utils.invoke
import com.metra.nbaintegra.core.viewmodel.AbstractViewModel

class PlayersViewModel(
    observePlayersUseCase: ObservePlayersUseCase,
) : AbstractViewModel<PlayersViewModel.State>(State()) {
    val players =
        observePlayersUseCase()
            .cachedIn(viewModelScope)

    data class State(
        val loading: Boolean = true,
    ) : AbstractViewModel.State
}
