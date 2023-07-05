package com.example.mg.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class MyTask( val description: String = "",
                      val status: Boolean = false,
                      val timestamp: String = "",
                      @PrimaryKey(autoGenerate = true) var id: Long = 0

) : Parcelable {


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



