package com.eugeneosipenko.stockdemo.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.eugeneosipenko.stockdemo.domain.RealtimePriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

private const val REALTIME_PRICE_UPDATE_INTERVAL = 15000L

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val realtimePriceUseCase: RealtimePriceUseCase,
) : ViewModel() {

    fun realtimePrice(symbol: String) = flow {
        while (true) {
            emit(realtimePriceUseCase(symbol).getOrThrow())
            Log.e("ZXC", "UPDATE")
            delay(REALTIME_PRICE_UPDATE_INTERVAL)
        }
    }
}
