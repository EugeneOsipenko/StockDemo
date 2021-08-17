package com.eugeneosipenko.stockdemo.model

data class Company(
    val name: String,
    val symbol: String,
    val price: Float,
    val exchange: String,
    val profile: CompanyProfile? = null
)
