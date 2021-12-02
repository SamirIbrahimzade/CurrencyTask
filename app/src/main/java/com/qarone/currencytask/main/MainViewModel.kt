package com.qarone.currencytask.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.data.models.RetrofitService
import com.qarone.currencytask.data.room.RoomRepository
import com.qarone.currencytask.use_case.GetRatesUseCase
import com.qarone.currencytask.util.Resource
import kotlinx.coroutines.*
import okhttp3.internal.notify
import java.util.*

class MainViewModel(

) : ViewModel() {

    private val mainRepositoryImpl: MainRepositoryImpl =
        MainRepositoryImpl(RetrofitService.currencyApi)
    private val roomRepository: RoomRepository = RoomRepository()
    private val getRatesUseCase: GetRatesUseCase = GetRatesUseCase(mainRepositoryImpl)
    private var apiJobs = mutableListOf<Job>()

    private val _ratesLiveData = MutableLiveData<List<Rate>>(emptyList())
    val ratesLiveData: LiveData<List<Rate>> = _ratesLiveData

    private val _baseCurrencyLiveData = MutableLiveData("GBP")
    val baseCurrencyLiveData: LiveData<String>
        get() = _baseCurrencyLiveData

    fun setBaseCurrency(currency: String) {
        _baseCurrencyLiveData.value = currency
    }

    private val finalRates: MutableList<Rate> = mutableListOf()

    fun getRatesFromApi() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                when (val response = getRatesUseCase.invoke(baseCurrencyLiveData.value!!)) {
                    is Resource.Success -> response.data?.let {
                        finalRates.clear()
                        finalRates.addAll(it)
                        saveRates()
                        delay(1000)
                    }
                    else -> Log.d("MVM", response.message.toString())
                }
            }
        }
        apiJobs.add(job)
    }

    private fun saveRates() {
        CoroutineScope(Dispatchers.IO).launch {
            for (rate in finalRates) {
                roomRepository.saveRate(rate)
            }
        }
    }

    fun getRatesFromDb() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val rates = roomRepository.getAllRates()
            _ratesLiveData.postValue(rates)
        }
        apiJobs.add(job)
    }


    fun changeAmount(amount: Int) {
        for (rate in finalRates){
            rate.rate *= amount
        }
        _ratesLiveData.postValue(finalRates)
    }

}