package com.toyota.oneapp.paypay_currencyconverter.network

interface NetworkCallback<T> {
    fun onSuccess(response: T)
    fun onError(error: String)
}