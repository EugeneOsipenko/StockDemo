package com.eugeneosipenko.stockdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyProfile(
    val symbol : String,
    val price : Double,
    val changes : Double,
    val companyName : String,
    val currency : String,
    val exchange : String,
    val description : String,
    val sector : String,
    val industry : String,
    val image : String,
    val lastDiv : Double
) : Parcelable
