package com.qarone.currencytask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qarone.currencytask.data.models.Rate

@Database(entities = [(Rate::class)], version = 1)
abstract class RateDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}