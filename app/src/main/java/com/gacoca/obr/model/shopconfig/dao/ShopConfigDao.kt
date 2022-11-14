package com.gacoca.obr.model.shopconfig.dao

import androidx.room.*
import com.gacoca.obr.model.inventory.entities.Category
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
}