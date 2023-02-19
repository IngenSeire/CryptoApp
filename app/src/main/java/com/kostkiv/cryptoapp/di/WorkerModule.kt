package com.kostkiv.cryptoapp.di

import com.kostkiv.cryptoapp.data.workers.ChildCoinWorkerFactory
import com.kostkiv.cryptoapp.data.workers.UpdateDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(UpdateDataWorker::class)
    fun bindUpdateDataWorkerFactory(worker : UpdateDataWorker.Factory) : ChildCoinWorkerFactory
}