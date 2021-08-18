package com.eugeneosipenko.stockdemo.domain

import com.eugeneosipenko.stockdemo.network.StockService
import com.eugeneosipenko.stockdemo.model.Company
import com.eugeneosipenko.stockdemo.model.CompanyProfile
import javax.inject.Inject

class StockListRepository @Inject constructor(
    private val service: StockService
) {

    suspend fun loadCompaniesList(): List<Company> {
        return service.loadCompaniesList()
    }

    suspend fun loadCompanyProfile(symbol: String): CompanyProfile {
        return service.loadCompanyProfile(symbol)[0]
    }
}
