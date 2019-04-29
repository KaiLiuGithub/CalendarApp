package com.kai.liu.calendar

import android.content.Context
import android.content.SharedPreferences

class CalendarSharedPreferences(context: Context) {
    var sharedPreferences = context.getSharedPreferences("calendar_shared_preferences", Context.MODE_PRIVATE)

    var YEAR_KEY = "YEAR_KEY"
    var MONTH_KEY = "MONTH_KEY"
    var DATE_KEY = "DATE_KEY"

    fun setYear(year: Int) {
        sharedPreferences.edit().putInt(YEAR_KEY, year).apply()
    }

    fun getYear(): Int {
        return sharedPreferences.getInt(YEAR_KEY, -1)
    }

    fun setMonth(month: Int) {
        sharedPreferences.edit().putInt(MONTH_KEY, month).apply()
    }

    fun getMonth(): Int {
        return sharedPreferences.getInt(MONTH_KEY, -1)
    }

    fun setDate(date: String) {
        sharedPreferences.edit().putString(DATE_KEY, date).apply()
    }

    fun getDate(): String {
        return sharedPreferences.getString(DATE_KEY, "")
    }
}