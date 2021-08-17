package com.eugeneosipenko.stockdemo.network

import com.eugeneosipenko.stockdemo.model.Company
import com.eugeneosipenko.stockdemo.model.CompanyProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface StockService {

    @GET("api/v3/stock/list")
    suspend fun loadCompaniesList(): List<Company>

    @GET("api/v3/profile/{symbol}")
    suspend fun loadCompanyProfile(@Path("symbol") symbol: String): List<CompanyProfile>
}
