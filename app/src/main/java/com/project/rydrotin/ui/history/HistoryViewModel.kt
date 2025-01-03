package com.project.rydrotin.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.rydrotin.local.HabitsDatabase
import com.project.rydrotin.local.entities.History
import com.project.rydrotin.local.entities.Mood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val database = HabitsDatabase.getInstance()

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>> = _history
    private val _dayHistory = MutableLiveData<List<History>?>()
    val dayHistory: LiveData<List<History>?> = _dayHistory
    private val _moodStatus = MutableLiveData<Mood?>()
    val moodStatus: LiveData<Mood?> = _moodStatus
    private val _state = MutableLiveData(false)
    val state = _state

    fun getHistory(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _history.postValue(database.historyDAO().getHistory(username))
        }
    }

    fun deleteHistory(historyId: Long, username: String) {
        viewModelScope.launch (Dispatchers.IO){
            database.historyDAO().deleteHistory(historyId, username)
        }
    }

    fun getTodayDetails(date: String, username: String){
        viewModelScope.launch (Dispatchers.IO){
            val historyJob = async{ database.historyDAO().getAllTodayHistory(date, username) }
            val moodJob = async { database.moodDAO().getTodayMood(date, username) }
            _dayHistory.postValue(historyJob.await())
            _moodStatus.postValue(moodJob.await())
            _state.postValue(true)
        }
    }

    fun clearDetails(){
        _dayHistory.value = null
        _state.value = false
    }
}