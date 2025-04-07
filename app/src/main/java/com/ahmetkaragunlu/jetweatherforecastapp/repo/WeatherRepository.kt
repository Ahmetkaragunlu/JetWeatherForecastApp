package com.ahmetkaragunlu.jetweatherforecastapp.repo

import android.util.Log
import com.ahmetkaragunlu.jetweatherforecastapp.data.DataOrException
import com.ahmetkaragunlu.jetweatherforecastapp.model.Weather
import com.ahmetkaragunlu.jetweatherforecastapp.network.WeatherApi

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = units)

        }catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return  DataOrException(data = response)

    }


}