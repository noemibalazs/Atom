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
import kotlinx.android.synthetic.main.fragment_hottest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.Comparator

class HottestFragment : Fragment() {

    private val TAG = HottestFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_hottest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    fun dataPresentInLocalStorage(): Boolean = true

    fun fetchData() {
        if (dataPresentInLocalStorage()) {
            val sortedList = fetchDataFromLocalStorage()
            hottestRecyclerView.adapter = ForecastAdapter(sortedList)
            initRecyclerView()
        } else {
            fetchDataFromServer()
        }
    }

    fun fetchDataFromServer() {
        fetchDataUsingRetrofit()
    }

    fun fetchDataFromLocalStorage(): MutableList<Weather> {
        val results = mutableListOf<Weather>()
        val myList = context!!.getWeatherOfflineList()
        for (item in myList) {
            if (item.chance_rain < 0.5) {
                results.add(item)
            }
        }

        Collections.sort(results, object : Comparator<Weather> {
            override fun compare(weather1: Weather?, weather2: Weather?): Int {
                return weather2!!.high.compareTo(weather1!!.high)
            }
        })
        return results
    }

    private fun initRecyclerView() {
        hottestRecyclerView.setHasFixedSize(true)
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
                hottestRecyclerView.adapter = ForecastAdapter(list)
                initRecyclerView()

            }
        })
    }
}