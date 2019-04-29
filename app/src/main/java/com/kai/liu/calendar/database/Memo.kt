package com.kai.liu.calendar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Memo(
        @PrimaryKey
        val date: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "memo")
        val memo: String,

        @ColumnInfo(name = "time")
        val time: String,

        @ColumnInfo(name = "location")
        val location: String
)