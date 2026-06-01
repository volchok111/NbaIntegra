package com.metra.di

import org.koin.dsl.module

private val networkModule = module { }

private val repositoryModule = module { }

private val useCaseModule = module { }

val appModules =
    listOf(
        networkModule,
        repositoryModule,
        useCaseModule,
    )
