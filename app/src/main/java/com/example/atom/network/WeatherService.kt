package com.example.atom.network

import com.example.atom.data.Weather
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {

    @GET("forecast")
    fun getWeatherList(): Call<List<Weather>>
}