package com.eugeneosipenko.stockdemo.domain

import com.eugeneosipenko.stockdemo.network.StockService
import javax.inject.Inject

class RealtimePriceRepository @Inject constructor(
    private val service: StockService
) {

    suspend fun realtimePrice(symbol: String): Double {
        return service.realtimePrice(symbol)[0].price
    }
}
