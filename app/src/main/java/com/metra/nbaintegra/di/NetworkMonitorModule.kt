package com.metra.nbaintegra.di

import com.metra.nbaintegra.core.network.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkMonitorModule =
    module {
        single {
            NetworkMonitor(
                context = androidContext(),
            )
        }
    }
