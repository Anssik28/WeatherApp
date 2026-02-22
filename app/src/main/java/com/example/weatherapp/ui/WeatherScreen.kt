package com.example.weatherapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen( viewModel: WeatherViewModel)
 {
    val state by viewModel.uiState.collectAsState()

    var city by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Syötä kaupunki") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.fetchWeather(city) }
        ) {
            Text("Hae sää")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }


            state.error != null -> {
                Text(state.error!!, color = Color.Red)
            }

            state.weather != null -> {
                state.weather?.let { weather ->
                    Text("City: ${weather.city}")
                    Text("Temp: ${weather.temperature} °C")
                    Text("Description: ${weather.description}")
                }
            }
        }
    }
}