package com.example.mg

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyTask(
    val id: Long,
    val description: String = "",
    val status: Boolean = false,
    val timestamp: String = ""
): Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyTask) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}


