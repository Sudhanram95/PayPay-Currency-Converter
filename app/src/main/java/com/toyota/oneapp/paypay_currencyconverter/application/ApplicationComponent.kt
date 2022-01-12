package com.toyota.oneapp.paypay_currencyconverter.application

import android.app.Application
import com.toyota.oneapp.paypay_currencyconverter.application.viewmodel.ViewModelFactoryModule
import com.toyota.oneapp.paypay_currencyconverter.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                        ActivityBuilderModule::class,
                        ViewModelFactoryModule::class,
                        NetworkModule::class])
interface ApplicationComponent : AndroidInjector<PayPayApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}