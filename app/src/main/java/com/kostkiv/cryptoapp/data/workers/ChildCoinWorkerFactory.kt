package com.kostkiv.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildCoinWorkerFactory {

    fun create(
        context: Context,
        workerParameters: WorkerParameters
    ) : ListenableWorker
}