package com.gacoca.obr.model.shopconfig.dao

import androidx.room.*
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.shopconfig.entities.Shop
import com.gacoca.obr.model.shopconfig.entities.TaxConfig

@Dao
interface ShopConfigDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTaxConfig(taxConfig: TaxConfig)

    @Query("SELECT * FROM TaxConfig")
    fun getTaxConfig(): TaxConfig

    @Transaction
    @Update
    fun updateTaxConfig(taxConfig: TaxConfig)


    @Query("SELECT EXISTS(SELECT * FROM TaxConfig)")
    fun isTaxConfigExist(): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShopDetails(shop: Shop)

    @Query("SELECT * FROM Shop")
    fun getShopDetails(): Shop

    @Transaction
    @Update
    fun updateShop(shop: Shop)
}