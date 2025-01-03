package com.project.rydrotin.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits_table")
data class Habits(
    @ColumnInfo(defaultValue = "user")
    var username: String,
    var name: String,
    var countPerDay: Int
) {
    @PrimaryKey(autoGenerate = true)
    var habitId: Long = 0L
}