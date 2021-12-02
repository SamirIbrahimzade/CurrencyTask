package com.qarone.currencytask.data.room

import com.qarone.currencytask.App
import com.qarone.currencytask.data.models.Rate


class RoomRepository {
    private val rateDao: RateDao = App.database.rateDao()

    fun saveRate(rate: Rate) {
        rateDao.insert(rate)
    }

    fun getAllRates() = rateDao.getAllRates()


}