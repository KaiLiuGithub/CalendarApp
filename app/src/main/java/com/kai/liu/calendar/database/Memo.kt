package com.kai.liu.calendar.database

import androidx.room.*
import java.util.*

@Entity
data class Memo(
        @PrimaryKey
        val date: String,

        @ColumnInfo(name = "memo_list")
        val list: List<MemoItem>
) {

}

data class MemoItem(
        val title: String,
        val memo: String,
        val time: String,
        val location: String
)