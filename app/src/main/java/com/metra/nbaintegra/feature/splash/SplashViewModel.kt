package com.metra.nbaintegra.feature.splash

import androidx.lifecycle.viewModelScope
import com.metra.nbaintegra.core.viewmodel.AbstractViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : AbstractViewModel<SplashViewModel.State>(State()) {
    init {
        viewModelScope.launch {
            delay(1500)
            state = state.copy(loading = false)
        }
    }

    data class State(
        val loading: Boolean = true,
    ) : AbstractViewModel.State
}
