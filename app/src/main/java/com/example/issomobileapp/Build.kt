package com.example.issomobileapp

import android.os.Parcel
import android.os.Parcelable

data class Build(
    val imageId: Int,
    val title: String,
    val city: String,
    val roomCount: Int,
    val price: Int,
    val size: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageId)
        parcel.writeString(title)
        parcel.writeString(city)
        parcel.writeInt(roomCount)
        parcel.writeInt(price)
        parcel.writeInt(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Build> {
        override fun createFromParcel(parcel: Parcel): Build {
            return Build(parcel)
        }

        override fun newArray(size: Int): Array<Build?> {
            return arrayOfNulls(size)
        }
    }
}