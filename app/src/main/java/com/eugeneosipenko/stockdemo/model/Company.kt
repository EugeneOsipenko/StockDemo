package com.eugeneosipenko.stockdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    val name: String,
    val symbol: String,
    val price: Float,
    val exchange: String,
    val profile: CompanyProfile? = null
) : Parcelable
