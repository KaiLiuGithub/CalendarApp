package com.kai.liu.calendar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
abstract class MemoDao {
    @Query("SELECT * FROM memo WHERE date = :date")
    abstract fun getMemo(date: String): LiveData<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun setMemo(memo: Memo)

    @Query("UPDATE memo SET memo_list = :list WHERE date = :date")
    abstract fun setMemoList(date: String, list: List<MemoItem>)

    @Delete
    abstract fun deleteMemo(memo: Memo)

    fun addMemo(date: String, memoItem: MemoItem) {
        getMemo(date).observeForever { memo ->
            val array = ArrayList(memo.list)
            array.add(memoItem)
            setMemoList(date, array)
        }
    }
}