package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.model.WeatherEntity
import kotlinx.coroutines.flow.Flow
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.remote.RetrofitInstance

class WeatherRepository(
    private val dao: WeatherDao
) {

    suspend fun getCachedWeather(city: String): WeatherEntity? {
        return dao.getWeatherForCity(city)
    }

    suspend fun saveWeather(weather: WeatherEntity) {
        dao.insertWeather(weather)
    }

    fun getWeatherHistory(): Flow<List<WeatherEntity>> {
        return dao.getWeatherHistory()
    }
    suspend fun fetchWeather(city: String): WeatherEntity {
        val response = RetrofitInstance.api.getWeather(
            city = city,
            apiKey = BuildConfig.OPENWEATHER_API_KEY
        )

        val entity = WeatherEntity(
            city = response.name,
            temperature = response.main.temp,
            description = response.weather.first().description,
            timestamp = System.currentTimeMillis()
        )

        dao.insertWeather(entity)
        return entity
    }
}