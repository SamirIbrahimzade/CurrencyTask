package com.qarone.currencytask.use_case

import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.data.models.RateResponse
import com.qarone.currencytask.main.MainRepository
import com.qarone.currencytask.util.Resource

class GetRatesUseCase(
    private val repository: MainRepository
) {

    suspend operator fun invoke(base: String): Resource<List<Rate>> {
        return repository.getRates(base)
    }
}