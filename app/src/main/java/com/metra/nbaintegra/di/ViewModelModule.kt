package com.metra.nbaintegra.di

import com.metra.nbaintegra.feature.playerdetails.PlayerDetailsViewModel
import com.metra.nbaintegra.feature.players.PlayersViewModel
import com.metra.nbaintegra.feature.splash.SplashViewModel
import com.metra.nbaintegra.feature.teamdetails.TeamDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModelOf(::PlayersViewModel)
        viewModelOf(::PlayerDetailsViewModel)
        viewModelOf(::TeamDetailsViewModel)
        viewModelOf(::SplashViewModel)
    }
