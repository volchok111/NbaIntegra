package com.metra.di

import com.metra.domain.usecase.ObservePlayersUseCase
import org.koin.dsl.module

val useCaseModule =
    module {
        factory {
            ObservePlayersUseCase(
                repository = get(),
            )
        }
    }
