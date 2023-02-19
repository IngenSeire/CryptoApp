package com.kostkiv.cryptoapp.di

import android.app.Application
import com.kostkiv.cryptoapp.presentation.CoinApp
import com.kostkiv.cryptoapp.presentation.CoinPriceListActivity
import com.kostkiv.cryptoapp.presentation.FragmentCoinDetailInfo
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity : CoinPriceListActivity)

    fun inject(fragment : FragmentCoinDetailInfo)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application : Application) : ApplicationComponent
    }
}