package com.kostkiv.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kostkiv.cryptoapp.R
import com.kostkiv.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.kostkiv.cryptoapp.domain.CoinInfo
import com.kostkiv.cryptoapp.presentation.coin_recycler_view.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _binding : ActivityCoinPriceListBinding? = null
    private val binding : ActivityCoinPriceListBinding
    get() = _binding ?: throw RuntimeException("ActivityCoinPriceListBinding == null")

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        val adapter = CoinInfoAdapter(this)
        if(binding.FragmentContainerMainActivityLand == null) {
            adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinFullInfo: CoinInfo) {
                    val intent = CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        coinFullInfo.fromSymbol
                    )
                    startActivity(intent)
                }
            }
        } else {
            adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinFullInfo: CoinInfo) {
                    val fragment = FragmentCoinDetailInfo.newInstance(coinFullInfo.fromSymbol)
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.FragmentContainerMainActivityLand, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        binding.recyclerViewCoinPriceList.adapter = adapter
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}