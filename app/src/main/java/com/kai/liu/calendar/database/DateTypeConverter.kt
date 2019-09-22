package com.kai.liu.calendar.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}

class MemoListConverter {
    @TypeConverter
    fun stringToList(data: String): List<MemoItem> {
        return Gson().fromJson(data, Array<MemoItem>::class.java).toList()
    }

    @TypeConverter
    fun listToString(list: List<MemoItem>): String {
        return Gson().toJson(list)
    }
}