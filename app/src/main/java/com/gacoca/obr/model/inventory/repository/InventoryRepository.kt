package com.gacoca.obr.model.inventory.repository

import com.gacoca.obr.model.inventory.dao.InventoryDao
import com.gacoca.obr.model.inventory.entities.Category

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
}