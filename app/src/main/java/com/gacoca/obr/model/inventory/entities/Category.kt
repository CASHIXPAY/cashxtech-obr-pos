package com.gacoca.obr.model.inventory.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category @JvmOverloads constructor (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id :Int,

    @ColumnInfo(name = "category_name")
    var categoryName: String,


    ) {

    @Ignore var checked: Boolean = false
}
