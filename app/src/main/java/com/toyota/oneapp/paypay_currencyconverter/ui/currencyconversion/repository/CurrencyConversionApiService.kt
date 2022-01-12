package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.repository

import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConversionApiService {

    @GET("/live")
    suspend fun getCurrencyConversionRates(
        @Query("access_key") accessKey: String = "45cef1245a42126cf120182fae8ee920",
        @Query("currencies") currencies: String = "USD, AUD, CAD, PLN, MXN",
        @Query("format") format: Int = 1
    ): Response<CurrencyConversionResponse>
}