package com.qarone.currencytask.data.models
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rate_table")
data class Rate(
    @SerializedName("alphaCode")
    val alphaCode: String,
    @SerializedName("code")
    @PrimaryKey @NonNull val code: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("inverseRate")
    val inverseRate: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("numericCode")
    val numericCode: String,
    @SerializedName("rate")
    var rate: Double
)