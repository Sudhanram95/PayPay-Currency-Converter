package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toyota.oneapp.paypay_currencyconverter.databinding.ItemOtherCurrencyBinding
import com.toyota.oneapp.paypay_currencyconverter.ui.common.databinding.BindableRecyclerViewAdapter
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionUIModel

class ConversionAdapter(
    private var conversionList: List<CurrencyConversionUIModel>
): RecyclerView.Adapter<ConversionAdapter.ConversionViewHolder>(),
    BindableRecyclerViewAdapter<CurrencyConversionUIModel> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionViewHolder {
        val binding = ItemOtherCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversionViewHolder(binding)
    }

    inner class ConversionViewHolder(private val binding: ItemOtherCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uiModel: CurrencyConversionUIModel) {
            binding.uiModel = uiModel
        }
    }

    override fun getItemCount(): Int {
        return conversionList.size
    }


    override fun onBindViewHolder(holder: ConversionViewHolder, position: Int) {
        holder.bind(conversionList[position])
    }

    override fun setData(data: List<CurrencyConversionUIModel>?) {
        conversionList = data ?: emptyList()
        notifyDataSetChanged()
    }
}