package com.project.rydrotin.local

import androidx.room.*
import com.project.rydrotin.local.entities.History

@Dao
interface HistoryDAO {

    @Query("select * from history_table WHERE username = :username")
    fun getHistory(username: String): List<History>

    @Query("select * from history_table WHERE date = :date and habitId = :habitId and username = :username")
    fun getTodayHistory(date: String, habitId: Long, username: String): History?

    @Query("select * from history_table WHERE date = :date and username = :username")
    fun getAllTodayHistory(date: String, username: String): List<History>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History?)

    @Query("DELETE from history_table WHERE historyId = :historyId and username = :username")
    suspend fun deleteHistory(historyId: Long, username: String): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHistory(history: History)

}