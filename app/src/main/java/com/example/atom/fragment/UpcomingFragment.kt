package com.example.atom.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.atom.R
import com.example.atom.adapter.ForecastAdapter
import com.example.atom.data.Weather
import com.example.atom.network.WeatherClient
import com.example.atom.network.WeatherService
import com.example.atom.utils.getWeatherOfflineList
import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingFragment : Fragment() {

    private val TAG = UpcomingFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    fun dataPresentInLocalStorage(): Boolean = true

    fun fetchData() {
        if (dataPresentInLocalStorage()) {
            val weatherList = fetchDataFromLocalStorage()
            upcomingRecyclerView.adapter = ForecastAdapter(weatherList)
            initRecyclerView()
        } else {
            fetchDataFromServer()
        }
    }

    fun fetchDataFromLocalStorage(): MutableList<Weather> {
        return context!!.getWeatherOfflineList()
    }

    private fun initRecyclerView() {
        upcomingRecyclerView.setHasFixedSize(true)
    }

    fun fetchDataFromServer() {
        fetchDataUsingRetrofit()
    }

    private fun fetchDataUsingRetrofit() {
        val weatherClient = WeatherClient.getRetrofitInstance().create(WeatherService::class.java)
        val myList = weatherClient.getWeatherList()
        myList.enqueue(object : Callback<List<Weather>> {

            override fun onFailure(call: Call<List<Weather>>, th: Throwable) {
                Log.d(TAG, "onFailure: ${th.message}")
            }

            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {

                Log.d(TAG, "onResponse: ${response.message()}")

                if (!response.isSuccessful) {
                    Log.d(TAG, "Error: ${response.code()}")
                    return
                }

                val list = response.body()!!
                upcomingRecyclerView.adapter = ForecastAdapter(list)
                upcomingRecyclerView.setHasFixedSize(true)

            }
        })
    }
}