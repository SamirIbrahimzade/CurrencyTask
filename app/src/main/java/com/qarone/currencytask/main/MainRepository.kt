package com.qarone.currencytask.main

import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.data.models.RateResponse
import com.qarone.currencytask.util.Resource

interface MainRepository {

    suspend fun getRates(base: String): Resource<List<Rate>>
}