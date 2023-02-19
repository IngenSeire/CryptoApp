package com.kostkiv.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kostkiv.cryptoapp.domain.GetCoinInfoListUseCase
import com.kostkiv.cryptoapp.domain.GetCoinInfoUseCase
import com.kostkiv.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSymbol : String) = getCoinInfoUseCase(fromSymbol = fSymbol)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}