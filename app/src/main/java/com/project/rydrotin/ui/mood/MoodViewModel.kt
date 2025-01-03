package com.project.rydrotin.ui.mood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.rydrotin.local.HabitsDatabase
import com.project.rydrotin.local.entities.Mood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoodViewModel : ViewModel() {

    private val database = HabitsDatabase.getInstance()

    private val _moodHistory = MutableLiveData<List<Mood>>()
    val moodHistory: LiveData<List<Mood>> = _moodHistory
    private val _mood = MutableLiveData<Mood?>()
    val mood: LiveData<Mood?> = _mood
    private val _state = MutableLiveData(false)
    val state = _state

    fun insertMood(mood: Mood) {
        viewModelScope.launch(Dispatchers.IO) {
            database.moodDAO().insertMood(mood)
        }
    }

    fun updateMood(mood: Mood) {
        viewModelScope.launch(Dispatchers.IO) {
            database.moodDAO().updateMood(mood)
        }
    }

    fun checkTodayMood(date: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = async { database.moodDAO().getTodayMood(date, username) }
            _mood.postValue(job.await())
            _state.postValue(true)
        }
    }

    fun getMoodHistory(username: String){
        viewModelScope.launch (Dispatchers.IO){
            _moodHistory.postValue(database.moodDAO().getMoodHistory(username))
        }
    }

    fun clearTodayMood() {
        _mood.value = null
        _state.value = false
    }
}