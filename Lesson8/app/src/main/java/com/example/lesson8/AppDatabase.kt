package com.example.lesson8

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson8.AppDatabase.Companion.DATABASE_VERSION


@Database(
    entities = [ProductEntity::class],
    version = DATABASE_VERSION,
)
//это свои конвертеры данных, который база сама не можешь распарсить
//@TypeConverters(
//    ListStringConverter::class,
//)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_VERSION = 2
    }

    abstract fun createInspectionDao(): ProductDao
}
