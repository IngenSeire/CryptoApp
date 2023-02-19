package com.kostkiv.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.kostkiv.cryptoapp.data.database.CoinInfoDao
import com.kostkiv.cryptoapp.data.mapper.CoinMapper
import com.kostkiv.cryptoapp.data.workers.UpdateDataWorker
import com.kostkiv.cryptoapp.domain.CoinInfo
import com.kostkiv.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper : CoinMapper) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinList()) {
            it.map { dbModel -> mapper.mapDbModelToEntity(dbModel) }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            UpdateDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            UpdateDataWorker.makeRequest()
        )
    }

}