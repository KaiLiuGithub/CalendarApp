package com.kai.liu.calendar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*
import kotlin.concurrent.thread

@Database(entities = [(Memo::class)], version = 1)
@TypeConverters(MemoListConverter::class)
abstract class MemoDatabase: RoomDatabase() {

    companion object {
        var DB_NAME = "memoDatabase.db"

        fun createMemoDatabase(context: Context): MemoDatabase {
            return Room.databaseBuilder(context, MemoDatabase::class.java, DB_NAME).build()
        }
    }

    abstract fun memoDao(): MemoDao

    fun setMemo(memo: Memo) {
        thread {
            memoDao().setMemo(memo)
        }
    }

    fun getMemo(date: String): LiveData<Memo> {
        return memoDao().getMemo(date)
    }

    fun addMemo(date: String, memoItem: MemoItem) {
        memoDao().addMemo(date, memoItem)
    }
}