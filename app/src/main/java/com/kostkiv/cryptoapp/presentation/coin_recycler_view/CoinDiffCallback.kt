package com.kostkiv.cryptoapp.presentation.coin_recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.kostkiv.cryptoapp.domain.CoinInfo

object CoinDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}