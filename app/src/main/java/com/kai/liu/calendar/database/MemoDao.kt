package com.kai.liu.calendar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo WHERE date = :date")
    fun getMemo(date: String): LiveData<Memo>

    @Insert
    fun setMemo(memo: Memo)

    @Delete
    fun deleteMemo(memo: Memo)
}