package com.qarone.currencytask.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qarone.currencytask.R
import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.databinding.ActivityMainBinding
import android.widget.AdapterView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var ratesAdapter: RateAdapter
    private lateinit var ratesArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getRatesFromApi()
        ratesArray = resources.getStringArray(R.array.codes)
        setupRatesRecyclerView()
        configureSpinner()
        configureEditText()
        configureLiveDataObservers()
    }

    private fun configureSpinner() {
        binding.spCurrencyActivityMain.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setBaseCurrency(ratesArray[position])
                    viewModel.getRatesFromDb()
                }
            }
    }

    private fun configureEditText() {
        binding.etAmountActivityMain.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                var amount = s.toString()
                if (amount == "") {
                    amount = "1"
                }
                viewModel.changeAmount(amount.toInt())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setupRatesRecyclerView() {
        ratesAdapter = RateAdapter()
        binding.rvRatesActivityMain.apply {
            adapter = ratesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    private fun updateRecyclerView(list: List<Rate>) {
        ratesAdapter.clearData()
        for (rate in list) {
            ratesAdapter.addData(rate)
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.ratesLiveData.observe(this, { list ->
            updateRecyclerView(list)

            if (list.isEmpty()) {
                binding.pbActivityMain.visibility = View.VISIBLE
            } else {
                binding.pbActivityMain.visibility = View.GONE
            }
        })
    }


}