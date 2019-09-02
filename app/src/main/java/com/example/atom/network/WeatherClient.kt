package com.example.atom.network

import com.example.atom.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherClient {

    companion object{

        var retrofit: Retrofit ?= null

        fun getRetrofitInstance(): Retrofit{

            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY}
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            if (retrofit == null){
                retrofit =  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
    }
}