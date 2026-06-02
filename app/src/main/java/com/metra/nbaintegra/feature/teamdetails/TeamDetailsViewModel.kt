package com.metra.nbaintegra.feature.teamdetails

import androidx.lifecycle.viewModelScope
import com.metra.domain.model.TeamDetailsModel
import com.metra.domain.usecase.GetTeamDetailsUseCase
import com.metra.domain.utils.Data
import com.metra.nbaintegra.core.utils.AbstractViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class TeamDetailsViewModel(
    private val getTeamDetailsUseCase: GetTeamDetailsUseCase,
) : AbstractViewModel<TeamDetailsViewModel.State>(State()) {
    fun loadTeam(teamId: Int) {
        if (state.team?.id == teamId) {
            return
        }

        viewModelScope.launch {
            state =
                state.copy(
                    loading = true,
                    team = null,
                )

            state =
                when (val result = getTeamDetailsUseCase(teamId)) {
                    is Data.Success -> {
                        state.copy(
                            loading = false,
                            team = result.value,
                        )
                    }

                    is Data.Error -> {
                        Timber.e(result.cause, "Failed to load team details. id=$teamId")

                        state.copy(
                            loading = false,
                            team = null,
                        )
                    }
                }
        }
    }

    data class State(
        val loading: Boolean = true,
        val team: TeamDetailsModel? = null,
    ) : AbstractViewModel.State
}
