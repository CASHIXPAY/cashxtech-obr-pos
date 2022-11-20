package com.gacoca.obr.model.shopconfig.repository


import com.gacoca.obr.model.shopconfig.dao.ShopConfigDao
import com.gacoca.obr.model.shopconfig.entities.Shop
import com.gacoca.obr.model.shopconfig.entities.TaxConfig

class ShopConfigRepository(private val shopConfigDao: ShopConfigDao) {


    fun saveTaxConfig(taxConfig: TaxConfig) {

        shopConfigDao.insertTaxConfig(taxConfig)
    }

    fun getTaxConfig(): TaxConfig {

        return shopConfigDao.getTaxConfig()
    }

    fun updateTaxConfig(taxConfig: TaxConfig) {

        return shopConfigDao.updateTaxConfig(taxConfig)
    }

    fun isTaxConfigExist(): Boolean {

        return shopConfigDao.isTaxConfigExist()
    }

    fun saveShopDetails(shop: Shop) {

        shopConfigDao.insertShopDetails(shop)
    }

    fun getShopDetails(): Shop {

        return shopConfigDao.getShopDetails()
    }

    fun updateShopDetails(shop: Shop) {

        shopConfigDao.updateShop(shop)
    }

    fun isShopConfigExist(): Boolean {

        return shopConfigDao.isShopConfigExist()
    }
}