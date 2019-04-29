package com.kai.liu.calendar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import java.util.*
import kotlin.concurrent.thread

@Database(entities = [(Memo::class)], version = 1)
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

}