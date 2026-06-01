package com.metra.nbaintegra.feature.playerdetails

import androidx.lifecycle.viewModelScope
import com.metra.domain.model.PlayerDetailsModel
import com.metra.domain.usecase.GetPlayerDetailsUseCase
import com.metra.domain.utils.Data
import com.metra.nbaintegra.core.utils.AbstractViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class PlayerDetailsViewModel(
    private val getPlayerDetailsUseCase: GetPlayerDetailsUseCase,
) : AbstractViewModel<PlayerDetailsViewModel.State>(State()) {
    fun onLoadPlayer(playerId: Int) {
        if (state.player?.id == playerId) {
            return
        }

        viewModelScope.launch {
            state =
                state.copy(
                    loading = true,
                )

            val result = getPlayerDetailsUseCase(playerId)
            state =
                when (result) {
                    is Data.Success -> {
                        state.copy(
                            loading = false,
                            player = result.value,
                        )
                    }

                    is Data.Error -> {
                        Timber.e(result.cause, "Failed to load player details")
                        state.copy(
                            loading = false,
                            player = null,
                        )
                    }
                }
        }
    }

    data class State(
        val loading: Boolean = true,
        val player: PlayerDetailsModel? = null,
    ) : AbstractViewModel.State
}
