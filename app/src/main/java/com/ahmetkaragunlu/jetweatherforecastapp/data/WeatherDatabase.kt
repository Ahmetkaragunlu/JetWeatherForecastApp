package com.ahmetkaragunlu.jetweatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmetkaragunlu.jetweatherforecastapp.model.Favorite
import com.ahmetkaragunlu.jetweatherforecastapp.model.WeatherUnit

@Database(entities = [Favorite::class, WeatherUnit::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}