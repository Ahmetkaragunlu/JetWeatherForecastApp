package com.ahmetkaragunlu.jetweatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmetkaragunlu.jetweatherforecastapp.screens.AboutScreen
import com.ahmetkaragunlu.jetweatherforecastapp.screens.SearchScreen
import com.ahmetkaragunlu.jetweatherforecastapp.screens.WeatherSplashScreen
import com.ahmetkaragunlu.jetweatherforecastapp.screens.favorites.FavoritesScreen
import com.ahmetkaragunlu.jetweatherforecastapp.screens.main.MainScreen
import com.ahmetkaragunlu.jetweatherforecastapp.screens.main.MainViewModel
import com.ahmetkaragunlu.jetweatherforecastapp.screens.settings.SettingsScreen


@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name ) {
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        //www.google.com/cityname="seattle"
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                })){ navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel,
                    city = city)
            }


        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }

        composable(WeatherScreens.FavoriteScreen.name){
            FavoritesScreen(navController = navController)
        }

    }
}