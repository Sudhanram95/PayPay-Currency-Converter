package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyConversionResponse(
    val success: String?,
    val source: String?,
    val quotes: Map<String, Float>?,
    val error: ErrorModel?
): Parcelable

@Parcelize
data class ErrorModel(
    val code: Int,
    val info: String
): Parcelable
