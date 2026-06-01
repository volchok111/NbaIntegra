package com.metra.nbaintegra.feature.players

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.metra.domain.usecase.ObservePlayersUseCase
import com.metra.domain.utils.invoke
import com.metra.nbaintegra.core.utils.AbstractViewModel

class PlayersViewModel(
    observePlayersUseCase: ObservePlayersUseCase,
) : AbstractViewModel<PlayersViewModel.State>(State()) {
    val players =
        observePlayersUseCase()
            .cachedIn(viewModelScope)

    // TODO: Add loading while downloading items from API
    data class State(
        val loading: Boolean = true,
    ) : AbstractViewModel.State

    // TODO: Fix bug when run app for the > second time data are not visible
}
