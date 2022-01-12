package com.toyota.oneapp.paypay_currencyconverter.ui.common.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {

    @BindingAdapter(value = ["adapterList"], requireAll = false)
    @JvmStatic
    fun <T> setRecyclerViewAdapterData(
        recyclerView: RecyclerView,
        data: List<T>?
    ) {
        if (recyclerView.adapter is BindableRecyclerViewAdapter<*>) {
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as BindableRecyclerViewAdapter<T>).apply {
                setData(data)
            }

        }
    }
}