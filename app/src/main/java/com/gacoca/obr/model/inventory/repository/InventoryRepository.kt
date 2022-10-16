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


    fun saveProduct(product: Product){

        inventoryDao.insertProduct(product)
    }


}