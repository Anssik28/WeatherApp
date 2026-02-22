package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.weatherapp.data.local.AppDatabase
import com.example.weatherapp.data.model.WeatherEntity
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val weather: WeatherEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "weather_database"
    ).build()

    private val repository = WeatherRepository(database.weatherDao())

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun fetchWeather(city: String) {
        if (city.isBlank()) {
            _uiState.value = WeatherUiState(error = "Anna kaupungin nimi")
            return
        }

        viewModelScope.launch {
            _uiState.value = WeatherUiState(isLoading = true)

            try {
                val weather = repository.fetchWeather(city)
                _uiState.value = WeatherUiState(weather = weather)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState(error = e.toString())
            }
        }
    }
}