package com.kostkiv.cryptoapp.di

import android.app.Application
import com.kostkiv.cryptoapp.data.database.CoinDatabase
import com.kostkiv.cryptoapp.data.database.CoinInfoDao
import com.kostkiv.cryptoapp.data.network.ApiFactory
import com.kostkiv.cryptoapp.data.network.ApiService
import com.kostkiv.cryptoapp.data.repository.CoinRepositoryImpl
import com.kostkiv.cryptoapp.domain.CoinRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCoinRepository(impl : CoinRepositoryImpl) : CoinRepository {
        return impl
    }

    @Provides
    @ApplicationScope
    fun provideCoinInfoDao(application: Application) : CoinInfoDao {
        return CoinDatabase.getInstance(application).coinPriceInfoDao()
    }

    @Provides
    @ApplicationScope
    fun provideApiService() : ApiService {
        return ApiFactory.apiService
    }
}