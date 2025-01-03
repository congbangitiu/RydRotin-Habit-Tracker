package com.project.rydrotin.local

import androidx.room.*
import com.project.rydrotin.local.entities.HabitWithHistory
import com.project.rydrotin.local.entities.Habits


@Dao
interface HabitsDAO {

    @Query("select * from habits_table WHERE username = :username")
    fun getHabits(username: String): List<Habits>

    @Insert
    suspend fun insertHabit(habits: Habits?)

    @Query("DELETE from habits_table WHERE habitId = :habitId and username = :username")
    suspend fun deleteHabit(habitId: Long, username: String): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHabit(habits: Habits)

    @Transaction
    @Query("SELECT * FROM habits_table WHERE habitId = :habitId and username = :username")
    fun getHabitWithHistoryById(habitId: Long, username: String): HabitWithHistory?
}