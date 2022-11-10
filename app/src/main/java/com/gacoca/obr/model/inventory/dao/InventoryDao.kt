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

    @Query("SELECT * FROM Product WHERE product_name = :productName")
    fun getProductByName(productName: String): Product

    @Query("SELECT * FROM Category WHERE category_name = :categoryName")
    fun getCategoryByName(categoryName: String): Category

    @Transaction
    @Query("SELECT * FROM Product WHERE category_name = :categoryName")
    fun getProductsByCategory(categoryName:String): List<Product>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Transaction
    @Query("DELETE FROM Category WHERE id = :id")
    fun deleteCategory(id:Int)

    @Query("SELECT category_name FROM Category")
    fun getCategoryNames():List<String>

    @Query("SELECT EXISTS(SELECT * FROM category WHERE category_name = :categoryName)")
    fun isCategoryExist(categoryName : String) : Boolean

    @Query("SELECT EXISTS(SELECT * FROM product WHERE product_name = :productName)")
    fun isProductExist(productName : String) : Boolean

    @Transaction
    @Query("DELETE FROM Product WHERE id = :id")
    fun deleteProduct(id:Int)

    @Transaction
    @Update
    fun updateProduct(product: Product)

    @Transaction
    @Update
    fun updateCategory(category: Category)



}