package com.gacoca.obr.model.shopconfig.repository


import com.gacoca.obr.model.shopconfig.dao.ShopConfigDao
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
}