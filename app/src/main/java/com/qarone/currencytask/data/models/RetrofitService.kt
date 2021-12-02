package com.qarone.currencytask.data.models

import com.qarone.currencytask.data.CurrencyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://tayqatech.com"

object RetrofitService {

    fun getRetrofitApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyApi: CurrencyApi by lazy { getRetrofitApi().create(CurrencyApi::class.java)
    }

}