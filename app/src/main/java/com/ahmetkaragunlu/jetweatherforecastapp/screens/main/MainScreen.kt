package com.ahmetkaragunlu.jetweatherforecastapp.screens.main


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmetkaragunlu.jetweatherforecastapp.data.DataOrException
import com.ahmetkaragunlu.jetweatherforecastapp.model.Weather
import com.ahmetkaragunlu.jetweatherforecastapp.model.WeatherItem
import com.ahmetkaragunlu.jetweatherforecastapp.navigation.WeatherScreens
import com.ahmetkaragunlu.jetweatherforecastapp.screens.settings.SettingsViewModel
import com.ahmetkaragunlu.jetweatherforecastapp.utils.formatDate
import com.ahmetkaragunlu.jetweatherforecastapp.utils.formatDecimals
import com.ahmetkaragunlu.jetweatherforecastapp.widgets.HumidityWindPressureRow
import com.ahmetkaragunlu.jetweatherforecastapp.widgets.SunsetSunRiseRow
import com.ahmetkaragunlu.jetweatherforecastapp.widgets.WeatherAppBar
import com.ahmetkaragunlu.jetweatherforecastapp.widgets.WeatherDetailRow
import com.ahmetkaragunlu.jetweatherforecastapp.widgets.WeatherStateImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    val curCity: String = if (city.isNullOrBlank()) "Seattle" else city
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }

    if (unitFromDb.isNotEmpty()) {
        unit = unitFromDb[0].units.split(" ")[0].lowercase()
        isImperial = unit == "imperial"
    }

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeatherData(city = curCity, units = unit)
    }.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data!!, navController, isImperial = isImperial)
        } else if (weatherData.e != null) {
            // Hata durumunu göster
            Text(text = "Hata: ${weatherData.e?.message}")
        }
    }
}

@Composable
fun MainScaffold(
    weather: Weather, navController: NavController, isImperial: Boolean
) {
    Scaffold(topBar = {
        WeatherAppBar(title = "${weather.city.name}, ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp) {
            Log.d("TAG", "MainScaffold: Button Clicked")
        }
    }) { paddingValues ->
        // paddingValues'ı kullanarak içeriği düzgün şekilde konumlandırın
        MainContent(data = weather, isImperial = isImperial, paddingValues = paddingValues)
    }
}

@Composable
fun MainContent(data: Weather, isImperial: Boolean, paddingValues: PaddingValues) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        Modifier
            .padding(paddingValues)  // Scaffold'dan gelen padding'i kullan
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Geri kalan kod aynı...

        Text(text = formatDate(weatherItem.dt), // Wed Nov 30
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))

        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)) {

            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = data.list[0], isImperial = isImperial)
        Divider()
        SunsetSunRiseRow(weather = data.list[0])
        Text("This Week",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold)

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)){
                items(items =  data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)

                }

            }

        }

    }

}