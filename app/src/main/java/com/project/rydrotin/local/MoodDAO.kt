package com.project.rydrotin.local

import androidx.room.*
import com.project.rydrotin.local.entities.Mood

@Dao
interface MoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: Mood?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMood(mood: Mood?)

    @Query("select * from mood_table WHERE date = :date and username = :username")
    fun getTodayMood(date: String, username: String): Mood?

    @Query("select * from mood_table WHERE username = :username")
    fun getMoodHistory(username: String): List<Mood>
}