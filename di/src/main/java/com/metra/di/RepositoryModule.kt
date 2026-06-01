package com.metra.di

import com.metra.data.repository.NbaRepositoryImpl
import com.metra.domain.repository.NbaRepository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<NbaRepository> {
            NbaRepositoryImpl(
                nbaApi = get(),
            )
        }
    }
