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
    var categoryName: String,

    @ColumnInfo(name = "product_name")
    var productName: String,

    @ColumnInfo(name = "product_price")
    var productPrice: Double,

    @ColumnInfo(name = "product_code")
    var productCode: String,

    @ColumnInfo(name = "product_unit")
    var productUnit: String

){

    @Ignore
    var checked: Boolean = false
}
