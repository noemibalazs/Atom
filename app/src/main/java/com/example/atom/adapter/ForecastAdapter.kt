package com.example.atom.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.atom.ui.DetailsActivity
import com.example.atom.R
import com.example.atom.data.Weather
import com.example.atom.utils.WEATHER
import com.example.atom.utils.loadPicture

class ForecastAdapter(val myList: List<Weather>) : RecyclerView.Adapter<ForecastAdapter.WeayherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeayherViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return WeayherViewHolder(view)
    }

    override fun getItemCount(): Int {
       return myList.size
    }

    override fun onBindViewHolder(holder: WeayherViewHolder, position: Int) {
        val weather = myList[position]
        holder.forecastText.text = Html.fromHtml("Day<span>&#92;</span <span>&#10092;</span>${weather.day}<span>&#10093;</span>: <span>&#92;</span <span>&#10092;</span>${weather.description}<span>&#10093;</span>")
        holder.forecastImage.context.loadPicture(weather.image, holder.forecastImage)
        holder.forecastImage.setOnClickListener {
            val intent = Intent(holder.forecastImage.context, DetailsActivity::class.java)
            intent.putExtra(WEATHER, weather)
            holder.forecastImage.context.startActivity(intent)
        }
    }

    inner class WeayherViewHolder(view: View): RecyclerView.ViewHolder(view){
        val forecastText = view.findViewById<TextView>(R.id.title)
        val forecastImage = view.findViewById<ImageView>(R.id.image)
    }

}