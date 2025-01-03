package com.project.rydrotin.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.rydrotin.HabitsApp.Companion.ctx
import com.project.rydrotin.local.entities.Habits
import com.project.rydrotin.local.entities.History
import com.project.rydrotin.local.entities.Mood

@Database(entities = [Habits::class, History::class, Mood::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 3,to = 4)])
abstract class HabitsDatabase : RoomDatabase(){
    abstract fun habitsDAO(): HabitsDAO
    abstract fun historyDAO(): HistoryDAO
    abstract fun moodDAO(): MoodDAO

    companion object {
        private var instance: HabitsDatabase? = null
        private val context = ctx

        @Synchronized
        fun getInstance(): HabitsDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, HabitsDatabase::class.java,
                    "habits_database")
                    .fallbackToDestructiveMigration()
                    .addMigrations(migration2To3)
                    .build()

            return instance!!
        }
        private val migration2To3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS mood_table ('username' TEXT NOT NULL, 'value' INTEGER NOT NULL,'message' TEXT NOT NULL, 'date' TEXT NOT NULL,'userName' TEXT NOT NULL, 'moodId' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
            }
        }
    }
}