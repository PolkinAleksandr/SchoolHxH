package com.example.lesson8

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    companion object {
        const val PRODUCT_ENTITY_NAME = "product_entity"
    }

    @Insert(entity = ProductEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM $PRODUCT_ENTITY_NAME")
    fun getProducts(): Flow<List<ProductEntity>>
}