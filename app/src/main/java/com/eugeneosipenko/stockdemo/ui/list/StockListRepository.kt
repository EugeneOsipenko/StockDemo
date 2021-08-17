package com.eugeneosipenko.stockdemo.ui.list

import com.eugeneosipenko.stockdemo.network.StockService
import com.eugeneosipenko.stockdemo.model.Company
import javax.inject.Inject

class StockListRepository @Inject constructor(
    private val service: StockService
) {

    suspend fun loadCompaniesList(): List<Company> {
        return service.loadCompaniesList()
    }
}
