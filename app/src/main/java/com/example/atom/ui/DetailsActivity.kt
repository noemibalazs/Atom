package com.example.atom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import com.example.atom.R
import com.example.atom.data.Weather
import com.example.atom.utils.WEATHER
import com.example.atom.utils.loadPicture
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var clicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val extra = intent

        if (extra == null) {
            Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_LONG).show()
            finish()
        }

        val weather = extra.getParcelableExtra<Weather>(WEATHER)
        supportActionBar?.title = Html.fromHtml("Day<span>&#92;</span <span>&#10092;</span>${weather.day}<span>&#10093;</span>")
        populateUI(weather)

        download.setOnClickListener {
            if (clicked){
                loadPicture(weather.image, detailsWeatherIV)
                download.visibility = View.GONE
                clicked = false
            }
        }
    }

    fun populateUI(weather: Weather) {
        detailsWeatherTitle.text = weather.description
        detailsWeatherMaxTemp.text = String.format(getString(R.string.max_temp_message), weather.high)
        detailsWeatherMinTemp.text = String.format(getString(R.string.min_temp_message), weather.low)
        detailsWeatherSunRise.text = String.format(getString(R.string.sunrise_at_message), weather.sunrise)
        detailsWeatherSunSet.text = String.format(getString(R.string.sunset_at_message), weather.sunset)
        val chance = weather.chance_rain * 100
        detailsWeatherChance.text = String.format(getString(R.string.chance_2_rain_message), chance, getString(
            R.string.chance_percent
        ))
    }
}
