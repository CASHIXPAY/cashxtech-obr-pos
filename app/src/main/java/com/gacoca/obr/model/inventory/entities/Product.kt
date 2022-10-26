package com.gacoca.obr.model.inventory.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "product_price")
    val productPrice: Double,

    @ColumnInfo(name = "product_code")
    val productCode: String,

    @ColumnInfo(name = "product_unit")
    val productUnit: String

){

    @Ignore
    var checked: Boolean = false
}
