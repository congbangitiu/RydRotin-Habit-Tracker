package com.project.rydrotin

import android.app.Application

class HabitsApp : Application() {

    var name: String? = null;

    fun setData(s: String) {
        this.name = s
    }

    fun getData(): String? {
        return name
    }

    companion object {
        lateinit var ctx: Application
    }

    override fun onCreate() {
        ctx = this
        super.onCreate()
    }
}