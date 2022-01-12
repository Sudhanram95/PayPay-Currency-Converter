package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.mapper

import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionUIModel
import javax.inject.Inject

class ConversionUIMapper @Inject constructor() {

    fun map(sourceCurrency: String, sourceAmount: Float, quotes: Map<String, Float>): List<CurrencyConversionUIModel> {
        val conversionList = mutableListOf<CurrencyConversionUIModel>()

        val sourceRate = quotes["USD${sourceCurrency}"] ?: 1.0f

        for (quote in quotes) {
            conversionList += CurrencyConversionUIModel(
                title = getConversionTitle(sourceCurrency, quote.key),
                amountLabelTxt = "Amount: ${String.format("%.2f", sourceAmount * calculateRate(sourceRate, quote.value))}",
                rateLabelTxt = "Rate: ${String.format("%.2f", calculateRate(sourceRate, quote.value))}"
            )
        }

        return conversionList
    }

    fun getConversionTitle(sourceCurrency: String, title: String): String {
        val otherCurrency = title.substring(3, title.length)
        return "$sourceCurrency to $otherCurrency"
    }

    fun calculateRate(sourceRateWithUSD: Float, otherRateWithUSD: Float) = (otherRateWithUSD / sourceRateWithUSD)
}