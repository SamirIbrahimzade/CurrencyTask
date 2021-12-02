package com.qarone.currencytask.main

import android.util.Log
import com.qarone.currencytask.data.CurrencyApi
import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.util.Resource

class MainRepositoryImpl(private val api: CurrencyApi) : MainRepository {

    private val TAG = "MainRepository"

    override suspend fun getRates(base: String): Resource<List<Rate>> = try {
        val response = api.getRates(base)
        val result = response.body()

        if (result != null) {
            Resource.Success(result)
        } else {
            Resource.Error("result is null")
        }
    } catch (e: Exception) {
        Log.d(TAG, e.toString())
        Resource.Error(e.toString())
    }

}