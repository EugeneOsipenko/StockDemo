package com.eugeneosipenko.stockdemo.model

data class CompanyProfile(
    val symbol : String,
    val price : Double,
    val changes : Double,
    val companyName : String,
    val currency : String,
    val exchange : String,
    val exchangeShortName : String,
    val description : String,
    val image : String
)
