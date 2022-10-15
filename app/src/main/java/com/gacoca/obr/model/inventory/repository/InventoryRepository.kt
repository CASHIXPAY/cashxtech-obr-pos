package com.gacoca.obr.model.inventory.repository

import com.gacoca.obr.model.inventory.dao.InventoryDao
import com.gacoca.obr.model.inventory.entities.Category

class InventoryRepository (private val inventoryDao: InventoryDao) {

    fun saveCategory(category: Category){

        inventoryDao.insertCategory(category)
    }
}