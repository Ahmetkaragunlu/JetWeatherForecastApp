A modern Android weather application built with Jetpack Compose, providing real-time and 7-day forecasts for cities around the world. Users can search for any city, view current and weekly weather data, and save favorite locations for quick access. The app is built using MVVM architecture, Room Database, Retrofit, and Dependency Injection via Hilt.

🔧 Technologies Used
Jetpack Compose – Modern, declarative UI for Android

Retrofit – HTTP client for consuming REST APIs (weather data)

Room Database – Local storage for saving favorite cities

Hilt (Dependency Injection) – Simplified dependency management

MVVM Architecture – Clean separation between UI, business logic, and data

Kotlin Coroutines & Flow – Asynchronous data handling and reactive streams

ViewModel – Manages UI-related data lifecycle-consciously

🌍 How It Works
Search Weather by City: Users can search any city in the world and instantly view the current weather and a 7-day forecast.

Save to Favorites: Frequently checked cities can be saved to a local favorites list using Room.

Real-Time Updates: Weather data is fetched via Retrofit from an external weather API.

Offline Access: Favorite cities are stored locally and remain available offline.

Clean Architecture: The app separates concerns using ViewModel, Repository, and DataSource layers.

Composable UI: Jetpack Compose dynamically reflects data changes with a smooth and reactive experience.
