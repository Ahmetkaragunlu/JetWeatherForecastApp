package com.ahmetkaragunlu.jetweatherforecastapp.data


import androidx.room.*
import com.ahmetkaragunlu.jetweatherforecastapp.model.Favorite
import com.ahmetkaragunlu.jetweatherforecastapp.model.WeatherUnit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    // Unit table
    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<WeatherUnit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: WeatherUnit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: WeatherUnit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: WeatherUnit)

}