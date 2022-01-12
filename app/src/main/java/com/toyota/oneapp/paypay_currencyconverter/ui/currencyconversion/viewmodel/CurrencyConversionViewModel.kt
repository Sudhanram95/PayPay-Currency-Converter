package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toyota.oneapp.paypay_currencyconverter.network.NetworkCallback
import com.toyota.oneapp.paypay_currencyconverter.network.NetworkState
import com.toyota.oneapp.paypay_currencyconverter.ui.common.lifecycle.SingleLiveEvent
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.mapper.ConversionUIMapper
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionResponse
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionUIModel
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.repository.CurrencyConversionRepository
import javax.inject.Inject

class CurrencyConversionViewModel @Inject constructor(
    private val repository: CurrencyConversionRepository,
    private val mapper: ConversionUIMapper
) : ViewModel() {

    private var sourceCurrency: String = ""

    private val _currencyConversionLiveData = SingleLiveEvent<NetworkState<CurrencyConversionResponse>>()
    val currencyConversionLiveData: LiveData<NetworkState<CurrencyConversionResponse>>
        get() = _currencyConversionLiveData

    private val _conversionList = MutableLiveData<List<CurrencyConversionUIModel>>()
    val conversionList: LiveData<List<CurrencyConversionUIModel>>
        get() = _conversionList

    private val _showErrorAlert = SingleLiveEvent<String>()
    val showErrorAlert: LiveData<String>
        get() = _showErrorAlert

    fun setSourceCurrency(source: String) {
        this.sourceCurrency = source
    }

    fun fetchCurrencyConversionRates() {
        _currencyConversionLiveData.value = NetworkState.Loading()

        repository.fetchAllCurrencyConversionRate(object : NetworkCallback<CurrencyConversionResponse?> {
            override fun onSuccess(response: CurrencyConversionResponse?) {
                response?.apply {
                    if (error == null) {
                        _currencyConversionLiveData.value = NetworkState.Success(response)
                    }
                    else
                        _currencyConversionLiveData.value = NetworkState.Error(Throwable(error.info))
                }
            }

            override fun onError(error: String) {
                _currencyConversionLiveData.value = NetworkState.Error(Throwable(error))
            }
        })
    }

    fun mapToUIModel(sourceAmount: Float = 1.0f, quoteMap: Map<String, Float>) {
        _conversionList.value = mapper.map(sourceCurrency, sourceAmount, quoteMap)
    }

    fun handleNetworkError(response: CurrencyConversionResponse?, errorMsg: String) {
        if (response != null) {
            _currencyConversionLiveData.value = NetworkState.Success(response)
        } else {
            _showErrorAlert.value = errorMsg
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }
}