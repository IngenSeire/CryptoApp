package com.kostkiv.cryptoapp.presentation

import android.app.Application
import com.kostkiv.cryptoapp.data.workers.CoinWorkerFactory
import com.kostkiv.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), androidx.work.Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    @Inject
    lateinit var coinWorkerFactory: CoinWorkerFactory

    override fun getWorkManagerConfiguration(): androidx.work.Configuration {
        return androidx.work.Configuration.Builder()
            .setWorkerFactory(coinWorkerFactory).build()
    }
}