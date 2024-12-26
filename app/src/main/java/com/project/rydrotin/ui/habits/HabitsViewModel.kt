package com.zkrallah.z_habits.ui.habits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zkrallah.z_habits.local.HabitsDatabase
import com.zkrallah.z_habits.local.entities.HabitWithHistory
import com.zkrallah.z_habits.local.entities.Habits
import com.zkrallah.z_habits.local.entities.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.lifecycle.SavedStateHandle

class HabitsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val database = HabitsDatabase.getInstance()

    private val _habits = MutableLiveData<List<Habits>>()
    val habits: LiveData<List<Habits>> = _habits
    private val _history = MutableLiveData<History?>()
    val history: LiveData<History?> = _history
    private val _habitHistory = MutableLiveData<HabitWithHistory?>()
    val habitHistory: LiveData<HabitWithHistory?> = _habitHistory
    private val _state = MutableLiveData(false)
    val state = _state

    fun getHistory(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _habits.postValue(database.habitsDAO().getHabits(username))
        }
    }

    fun insertHabit(habit: Habits) {
        viewModelScope.launch(Dispatchers.IO) {
            database.habitsDAO().insertHabit(habit)
        }
    }

    fun insertHistory(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            database.historyDAO().insertHistory(history)
        }
    }

    fun updateHistory(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            database.historyDAO().updateHistory(history)
        }
    }

    fun checkTodayHistory(habitId: Long, date: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = async { database.historyDAO().getTodayHistory(date, habitId, username) }
            _history.postValue(job.await())
            _state.postValue(true)
        }
    }

    fun clearTodayHistory() {
        _history.value = null
    }

    fun getHabitHistory(habitId: Long, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _habitHistory.postValue(database.habitsDAO().getHabitWithHistoryById(habitId, username))
        }
    }

    fun deleteHistory(historyId: Long, username: String) {
        viewModelScope.launch (Dispatchers.IO){
            database.historyDAO().deleteHistory(historyId, username)
        }
    }

    fun deleteHabit(habitId: Long, username: String){
        viewModelScope.launch (Dispatchers.IO){
            database.habitsDAO().deleteHabit(habitId, username)
        }
    }

    fun editHabit(habits: Habits){
        viewModelScope.launch (Dispatchers.IO) {
            database.habitsDAO().updateHabit(habits)
        }
    }


}