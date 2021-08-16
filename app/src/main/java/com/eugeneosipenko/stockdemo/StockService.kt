package com.eugeneosipenko.stockdemo

import com.eugeneosipenko.stockdemo.model.Company
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StockService {

    @GET("/v3/stock/list")
    suspend fun loadCompaniesList(): List<Company>
}
