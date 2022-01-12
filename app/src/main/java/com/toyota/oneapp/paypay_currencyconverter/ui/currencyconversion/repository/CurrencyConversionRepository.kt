package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.repository

import com.toyota.oneapp.paypay_currencyconverter.network.NetworkCallback
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionResponse
import kotlinx.coroutines.*
import javax.inject.Inject

class CurrencyConversionRepository @Inject constructor(
    private val apiService: CurrencyConversionApiService
) {

    var job: Job? = null

    fun fetchAllCurrencyConversionRate(networkCallback: NetworkCallback<CurrencyConversionResponse?>) {

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            networkCallback.onError(throwable.message.toString())
        }

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            while (true) {
                try {
                    val response = apiService.getCurrencyConversionRates()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            networkCallback.onSuccess(response.body())
                        } else {
                            networkCallback.onError(response.message())
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        networkCallback.onError(ex.message.orEmpty())
                    }
                }

                delay(30 * 60 * 1000)
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
        job = null
    }
}