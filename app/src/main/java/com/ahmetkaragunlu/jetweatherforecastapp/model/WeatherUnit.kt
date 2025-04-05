package com.ahmetkaragunlu.jetweatherforecastapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_tbl")
data class WeatherUnit(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val units: String)
