package com.toyota.oneapp.paypay_currencyconverter.application

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PayPayApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        private var instance: PayPayApplication? = null
        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}