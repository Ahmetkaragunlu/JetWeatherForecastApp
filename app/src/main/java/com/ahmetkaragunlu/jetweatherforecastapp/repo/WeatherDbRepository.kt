package com.ahmetkaragunlu.jetweatherforecastapp.repo


import com.ahmetkaragunlu.jetweatherforecastapp.data.WeatherDao
import com.ahmetkaragunlu.jetweatherforecastapp.model.Favorite
import com.ahmetkaragunlu.jetweatherforecastapp.model.WeatherUnit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<WeatherUnit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: WeatherUnit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: WeatherUnit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: WeatherUnit) = weatherDao.deleteUnit(unit)



}