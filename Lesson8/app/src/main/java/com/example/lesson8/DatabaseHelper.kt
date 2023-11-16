package com.example.lesson8

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseHelper {

    var database: AppDatabase? = null

    fun createDatabase(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    fun getProductDao() = database?.createInspectionDao()
}