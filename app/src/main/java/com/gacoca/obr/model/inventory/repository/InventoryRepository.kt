package com.gacoca.obr.model.inventory.repository

import com.gacoca.obr.model.inventory.dao.InventoryDao
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.entities.Product

class InventoryRepository (private val inventoryDao: InventoryDao) {

    fun saveCategory(category: Category){

        inventoryDao.insertCategory(category)
    }

    fun getCategories():List<Category>{

        return inventoryDao.getCategories()
    }

    fun removeCategory(categoryList: List<Category>){

        for(category in categoryList){

            inventoryDao.deleteCategory(category.id)
        }

    }

    fun getCategoryNames():List<String>{

        return inventoryDao.getCategoryNames()
    }

    fun isCategoryExist(categoryName:String):Boolean{
        return inventoryDao.isCategoryExist(categoryName)
    }


    fun saveProduct(product: Product){

        inventoryDao.insertProduct(product)
    }

    fun isProductExist(productName:String):Boolean{
        return inventoryDao.isProductExist(productName)
    }


    fun listProducts():List<Product>{

        return inventoryDao.getProducts()
    }

    fun removeProduct(productList: List<Product>){
        for(product in productList){
            inventoryDao.deleteProduct(product.id)
        }
    }

    fun getProductByName(productName: String):Product{

        return inventoryDao.getProductByName(productName)

    }

    fun getCategoryByName(categoryName: String):Category{

        return inventoryDao.getCategoryByName(categoryName)

    }

    fun updateProduct(product: Product){

        inventoryDao.updateProduct(product)
    }

    fun updateCategory(category: Category){

        inventoryDao.updateCategory(category)
    }

    fun updateProductCategories(oldCategoryName:String,newCategoryName:String){

        inventoryDao.updateProductCategories(oldCategoryName,newCategoryName)
    }


}