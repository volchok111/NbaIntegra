package com.metra.nbaintegra.di

import com.metra.nbaintegra.feature.players.PlayersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModel {
            PlayersViewModel(
                observePlayersUseCase = get(),
            )
        }
    }
