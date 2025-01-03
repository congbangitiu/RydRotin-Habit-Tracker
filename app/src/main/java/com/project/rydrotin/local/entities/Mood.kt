package com.project.rydrotin.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @ColumnInfo(defaultValue = "user")
    var username: String,
    var value: Int,
    var message: String,
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var moodId: Long = 0L
}
