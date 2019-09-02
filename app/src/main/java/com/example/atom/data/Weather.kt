package com.example.atom.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Weather(
    @field:SerializedName("day") val day: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("sunrise") val sunrise: Int,
    @field:SerializedName("sunset") val sunset: Int,
    @field:SerializedName("chance_rain") val chance_rain: Double,
    @field:SerializedName("high") val high: Int,
    @field:SerializedName("low") val low: Int,
    @field:SerializedName("image") val image: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readInt(),
        source.readDouble(),
        source.readInt(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(day)
        writeString(description)
        writeInt(sunrise)
        writeInt(sunset)
        writeDouble(chance_rain)
        writeInt(high)
        writeInt(low)
        writeString(image)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Weather> = object : Parcelable.Creator<Weather> {
            override fun createFromParcel(source: Parcel): Weather = Weather(source)
            override fun newArray(size: Int): Array<Weather?> = arrayOfNulls(size)
        }
    }
}