package com.metra.nbaintegra

import android.app.Application
import com.metra.di.appModules
import com.metra.nbaintegra.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NbaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NbaApplication)
            modules(
                appModules + viewModelModule,
            )
        }
    }
}
