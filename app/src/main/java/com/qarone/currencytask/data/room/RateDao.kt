package com.qarone.currencytask.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.qarone.currencytask.data.models.Rate

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: Rate)

    @Delete
    fun clearRates(vararg rate: Rate)

    @Query("SELECT * FROM rate_table")
    fun getAllRates(): List<Rate>
}