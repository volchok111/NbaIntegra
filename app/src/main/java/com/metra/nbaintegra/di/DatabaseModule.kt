package com.metra.nbaintegra.di

import androidx.room.Room
import com.metra.nbaintegra.local.NbaDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule =
    module {
        single {
            Room
                .databaseBuilder(
                    androidContext(),
                    NbaDatabase::class.java,
                    "nba_database",
                ).build()
        }

        single {
            get<NbaDatabase>().playerDao()
        }
    }
