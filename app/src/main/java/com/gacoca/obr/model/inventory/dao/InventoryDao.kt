package com.gacoca.obr.model.inventory.dao

import androidx.room.*
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.entities.Product



@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product: Product)


    @Transaction
    @Query("SELECT * FROM Product")
    fun getProducts(): List<Product>

    @Transaction
    @Query("SELECT * FROM Product WHERE category_name = :categoryName")
    fun getProductsByCategory(categoryName:String): List<Product>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

}