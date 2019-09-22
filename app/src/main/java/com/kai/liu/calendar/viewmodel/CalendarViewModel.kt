package com.kai.liu.calendar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kai.liu.calendar.CalendarSharedPreferences
import com.kai.liu.calendar.database.Memo
import com.kai.liu.calendar.database.MemoDatabase
import com.kai.liu.calendar.database.MemoItem
import java.util.*

class CalendarViewModel(application: Application): AndroidViewModel(application) {

    var calendarSharedPreferences = CalendarSharedPreferences(application)
    var memoDatabase = MemoDatabase.createMemoDatabase(application)

    fun init() {
        val date = Calendar.getInstance().time

        if (getDate().isBlank()) calendarSharedPreferences.setDate("2019/${date.month}/${date.date}")
        if (getMonth() < 0) calendarSharedPreferences.setMonth(date.month)
        if (getYear() < 0) calendarSharedPreferences.setYear(2019)
    }

    fun createDateList(): List<List<Int?>> {
        val dateList = arrayListOf<ArrayList<Int?>>()

        val cal = Calendar.getInstance()
        cal.set(calendarSharedPreferences.getYear(), calendarSharedPreferences.getMonth(), 1)

        dateList.add(arrayListOf())

        for (i in 1..cal.get(Calendar.DAY_OF_WEEK) - 1) {
            dateList[0].add(null)
        }

        var weekNum = 0
        for (i in 1..cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            if (dateList[weekNum].size >= 7) {
                weekNum++
                dateList.add(arrayListOf())
            }
            dateList[weekNum].add(i)
        }

        for (i in dateList[weekNum].size..7) {
            dateList[weekNum].add(null)
        }

        return dateList
    }

    fun addMemo(title: String, memo: String, time: String, location: String) {
        memoDatabase.addMemo(getDate(), MemoItem(title, memo, time, location))
    }

    fun getMemo(date: String = getDate()): LiveData<Memo> {
        return memoDatabase.getMemo(date)
    }

    fun setDate(date: String) {
        calendarSharedPreferences.setDate(date)
    }

    fun getDate(): String {
        return calendarSharedPreferences.getDate()
    }

    fun getYear(): Int {
        return calendarSharedPreferences.getYear()
    }

    fun getNextYear(): Int{
        calendarSharedPreferences.setYear(calendarSharedPreferences.getYear() + 1)
        return calendarSharedPreferences.getYear()
    }

    fun getPrevYear(): Int {
        calendarSharedPreferences.setYear(calendarSharedPreferences.getYear() - 1)
        return calendarSharedPreferences.getYear()
    }

    fun getMonth(): Int {
        return calendarSharedPreferences.getMonth()
    }

    fun getNextMonth(): String {
        if (getMonth() + 1 > 12) {
            calendarSharedPreferences.setYear(calendarSharedPreferences.getYear() + 1)
        }

        calendarSharedPreferences.setMonth((getMonth() + 1) % 12)

        return getMonthName()
    }

    fun getPrevMonth(): String {
        if (getMonth() - 1 < 0) {
            calendarSharedPreferences.setYear(calendarSharedPreferences.getYear() - 1)
        }

        calendarSharedPreferences.setMonth((getMonth() + 11) % 12)

        return getMonthName()
    }

    fun getMonthName(month: Int = getMonth()): String {
        when(month) {
            0 -> return "JANUARY"
            1 -> return "FEBRUARY"
            2 -> return "MARCH"
            3 -> return "APRIL"
            4 -> return "MAY"
            5 -> return "JUNE"
            6 -> return "JULY"
            7 -> return "AUGUST"
            8 -> return "SEPTEMBER"
            9 -> return "OCTOBER"
            10 -> return "NOVEMBER"
            else -> return "DECEMBER"
        }
    }
}