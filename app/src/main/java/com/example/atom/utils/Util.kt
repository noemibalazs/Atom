package com.example.atom.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.atom.R
import com.example.atom.data.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun Context.loadPicture(link: String, view: ImageView){
    Glide.with(this)
        .load(link)
        .error(R.drawable.nature)
        .placeholder(R.drawable.nature)
        .into(view)
}

fun Context.getWeatherAssetJson(fileName: String): String{

    var json:String = ""

    try {
        val assets = this.assets.open(fileName)
        val size = assets.available()
        val buffer = ByteArray(size)
        assets.read(buffer)
        assets.close()
        val charset = Charsets.UTF_8
        json = String(buffer, charset)

    }catch (e: IOException){
        e.printStackTrace()
    }

    return json
}

fun Context.getWeatherOfflineList(): MutableList<Weather>{
    val json = this.getWeatherAssetJson(WEATHER_ASSET)
    val type = object : TypeToken<MutableList<Weather>>(){}.type
    val myList = Gson().fromJson<MutableList<Weather>>(json, type)
    return myList
}