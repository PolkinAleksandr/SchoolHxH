package com.example.lesson8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductDao.PRODUCT_ENTITY_NAME)
data class ProductEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("name") val name: String,
)
