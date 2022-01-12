package com.toyota.oneapp.paypay_currencyconverter.ui.common.base

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity: DaggerAppCompatActivity() {

    lateinit var progressDialogFragment: ProgressDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        progressDialogFragment = ProgressDialogFragment()
        super.onCreate(savedInstanceState)
    }

    protected fun showProgress() {
        progressDialogFragment.showNow(supportFragmentManager, "ProgressDialog")
    }

    protected fun hideProgress() {
        progressDialogFragment.dismiss()
    }
}