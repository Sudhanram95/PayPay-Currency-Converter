package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.di

import androidx.lifecycle.ViewModel
import com.toyota.oneapp.paypay_currencyconverter.application.viewmodel.ViewModelKey
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.repository.CurrencyConversionApiService
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.viewmodel.CurrencyConversionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class CurrencyConversionModule {

    companion object {
        @Provides
        fun provideCurrencyConversionApiService(retrofit: Retrofit): CurrencyConversionApiService {
            return retrofit.create(CurrencyConversionApiService::class.java)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConversionViewModel::class)
    protected abstract fun bindCurrencyConversionViewModel(currencyConversionViewModel: CurrencyConversionViewModel): ViewModel
}