package com.project.rydrotin.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithHistory(
    @Embedded val habits: Habits,
    @Relation(
        parentColumn = "habitId",
        entityColumn = "habitId"
    )
    val history: List<History>
)