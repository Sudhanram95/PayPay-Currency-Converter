package com.toyota.oneapp.paypay_currencyconverter.application

import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.di.CurrencyConversionScope
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.di.CurrencyConversionModule
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.view.CurrencyConversionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @CurrencyConversionScope
    @ContributesAndroidInjector(modules = [CurrencyConversionModule::class])
    abstract fun contributeCurrencyConversionActivity() : CurrencyConversionActivity
}