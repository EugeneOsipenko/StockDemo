package com.eugeneosipenko.stockdemo.network

import com.eugeneosipenko.stockdemo.model.Company
import retrofit2.http.GET

interface StockService {

    @GET("api/v3/stock/list")
    suspend fun loadCompaniesList(): List<Company>
}
