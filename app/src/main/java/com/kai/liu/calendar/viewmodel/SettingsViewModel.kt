package com.kai.liu.calendar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kai.liu.calendar.CalendarSharedPreferences

class SettingsViewModel(application: Application): AndroidViewModel(application) {

    var calendarSharedPreferences = CalendarSharedPreferences(application)

    fun init() {

    }
}