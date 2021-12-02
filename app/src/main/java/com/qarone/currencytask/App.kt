package com.qarone.currencytask

import android.app.Application
import androidx.room.Room
import com.qarone.currencytask.data.room.RateDatabase

class App : Application() {

    companion object {
        lateinit var database: RateDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, RateDatabase::class.java, "rate_database")
            .build()
    }
}