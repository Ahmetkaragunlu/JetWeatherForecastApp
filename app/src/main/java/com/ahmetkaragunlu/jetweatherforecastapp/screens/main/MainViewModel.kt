package com.ahmetkaragunlu.jetweatherforecastapp.screens.main

import androidx.lifecycle.ViewModel
import com.ahmetkaragunlu.jetweatherforecastapp.data.DataOrException
import com.ahmetkaragunlu.jetweatherforecastapp.model.Weather
import com.ahmetkaragunlu.jetweatherforecastapp.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
)
    : ViewModel(){

    suspend fun getWeatherData(city: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)

    }


}