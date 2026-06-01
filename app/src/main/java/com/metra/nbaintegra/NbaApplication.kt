package com.metra.nbaintegra

import android.app.Application
import com.metra.di.appModules
import com.metra.nbaintegra.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber

class NbaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@NbaApplication)
            modules(
                appModules + viewModelModule +
                    module {
                        single(named("apiKey")) {
                            BuildConfig.BALLDONTLIE_API_KEY
                        }
                    },
            )
        }
    }
}
