package com.toyota.oneapp.paypay_currencyconverter.ui.common.localstorage

import android.content.Context
import com.google.gson.Gson
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionResponse

object PreferenceUtil {

    const val PREF_NAME = "PayPay_Preference"
    const val CONVERSION_RESPONSE_KEY = "CONVERSION_RESPONSE_KEY"

    fun saveResponse(context: Context, response: CurrencyConversionResponse) {
        val responseStr = Gson().toJson(response)

        val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(CONVERSION_RESPONSE_KEY, responseStr)
        editor.apply()
    }

    fun getResponseFromPreference(context: Context): CurrencyConversionResponse? {
        val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val responseStr = preference.getString(CONVERSION_RESPONSE_KEY, null)

        return Gson().fromJson(responseStr, CurrencyConversionResponse::class.java)
    }
}