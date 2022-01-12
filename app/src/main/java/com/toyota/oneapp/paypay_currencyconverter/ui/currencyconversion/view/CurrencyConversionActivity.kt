package com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toyota.oneapp.paypay_currencyconverter.R
import com.toyota.oneapp.paypay_currencyconverter.application.viewmodel.ViewModelFactory
import com.toyota.oneapp.paypay_currencyconverter.databinding.ActivityCurrencyConversionBinding
import com.toyota.oneapp.paypay_currencyconverter.network.NetworkState
import com.toyota.oneapp.paypay_currencyconverter.ui.common.base.BaseActivity
import com.toyota.oneapp.paypay_currencyconverter.ui.common.localstorage.PreferenceUtil
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.model.CurrencyConversionResponse
import com.toyota.oneapp.paypay_currencyconverter.ui.currencyconversion.viewmodel.CurrencyConversionViewModel
import kotlinx.android.synthetic.main.activity_currency_conversion.*
import javax.inject.Inject

class CurrencyConversionActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: CurrencyConversionViewModel
    lateinit var binding: ActivityCurrencyConversionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_conversion)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrencyConversionViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initializeView()
        addListeners()
        observeViewModelStates()
    }

    private fun initializeView() {
        val conversionAdapter = ConversionAdapter(emptyList())
        binding.rvConversions.apply {
            layoutManager = LinearLayoutManager(this@CurrencyConversionActivity, RecyclerView.VERTICAL, false)
            adapter = conversionAdapter
        }
    }

    private fun addListeners() {
        binding.spinnerSource.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setSourceCurrency(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
    }

    private fun observeViewModelStates() {
        viewModel.currencyConversionLiveData.observe(this, Observer {
            handleEvents(it)
        })

        viewModel.showErrorAlert.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun handleEvents(event: NetworkState<CurrencyConversionResponse>) {
        when(event) {
            is NetworkState.Loading -> {
                showProgress()
            }

            is NetworkState.Success -> {
                hideProgress()
                PreferenceUtil.saveResponse(this, event.data)
                event.data.quotes?.let {
                    binding.edtAmount.text.toString().apply {
                        val amount = if (isEmpty()) 1.0f
                                        else toFloat()
                        viewModel.mapToUIModel(amount, it)
                    }
                }
            }

            is NetworkState.Error -> {
                hideProgress()
                viewModel.handleNetworkError(PreferenceUtil.getResponseFromPreference(this), event.error.message.orEmpty())
            }
        }
    }
}