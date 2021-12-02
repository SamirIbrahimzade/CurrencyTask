package com.qarone.currencytask.data

import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.data.models.RateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/rates.php")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<List<Rate>>
}