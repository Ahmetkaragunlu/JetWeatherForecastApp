package com.ahmetkaragunlu.jetweatherforecastapp.network


import com.ahmetkaragunlu.jetweatherforecastapp.model.Weather
import com.ahmetkaragunlu.jetweatherforecastapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = API_KEY // your api key
    ): Weather
}