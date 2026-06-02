package com.metra.di

import com.metra.domain.usecase.GetPlayerDetailsUseCase
import com.metra.domain.usecase.GetTeamDetailsUseCase
import com.metra.domain.usecase.ObservePlayersUseCase
import org.koin.dsl.module

val useCaseModule =
    module {
        factory {
            ObservePlayersUseCase(
                repository = get(),
            )
        }

        factory {
            GetPlayerDetailsUseCase(
                repository = get(),
            )
        }

        factory {
            GetTeamDetailsUseCase(
                repository = get(),
            )
        }
    }
