package com.example.mg

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class MyTask(
    val id: Long,
    val description: String = "",
    val status: Boolean = false,
    val timestamp: String = ""
): Parcelable

