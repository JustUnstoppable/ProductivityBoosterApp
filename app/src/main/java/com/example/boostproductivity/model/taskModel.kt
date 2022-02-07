package com.example.boostproductivity.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class taskModel(
    val id: Int,
    val date:String?,
    val checkBox1: Boolean,
    val task1name: String?,
    val task1description: String?,
    val task1time: String?,
    val checkBox2: Boolean,
    val task2name: String?,
    val task2description: String?,
    val task2time: String?,
    val checkBox3: Boolean,
    val task3name: String?,
    val task3description: String?,
    val task3time: String?,
    val checkBox4: Boolean,
    val task4name: String?,
    val task4description: String?,
    val task4time: String?,
    val checkBox5: Boolean,
    val task5name: String?,
    val task5description: String?,
    val task5time: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(date)
        parcel.writeByte(if (checkBox1) 1 else 0)
        parcel.writeString(task1name)
        parcel.writeString(task1description)
        parcel.writeString(task1time)
        parcel.writeByte(if (checkBox2) 1 else 0)
        parcel.writeString(task2name)
        parcel.writeString(task2description)
        parcel.writeString(task2time)
        parcel.writeByte(if (checkBox3) 1 else 0)
        parcel.writeString(task3name)
        parcel.writeString(task3description)
        parcel.writeString(task3time)
        parcel.writeByte(if (checkBox4) 1 else 0)
        parcel.writeString(task4name)
        parcel.writeString(task4description)
        parcel.writeString(task4time)
        parcel.writeByte(if (checkBox5) 1 else 0)
        parcel.writeString(task5name)
        parcel.writeString(task5description)
        parcel.writeString(task5time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<taskModel> {
        override fun createFromParcel(parcel: Parcel): taskModel {
            return taskModel(parcel)
        }

        override fun newArray(size: Int): Array<taskModel?> {
            return arrayOfNulls(size)
        }
    }
}