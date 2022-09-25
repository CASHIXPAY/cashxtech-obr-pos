package com.gacoca.obr.model.invoice.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "invoice_item")
data class InvoiceItem(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "invoice_number_ref")
    val invoiceNumberRef:String,

    @ColumnInfo(name= "item_designation")
    val itemDesignation:String,

    @ColumnInfo(name = "item_quantity")
    val itemQuantity:Double,

    @ColumnInfo(name = "item_price")
    val itemPrice:Double,

    @ColumnInfo(name = "item_ct")
    val itemCt:Double,

    @ColumnInfo(name = "item_tl")
    val itemTl:Double,

    @ColumnInfo(name = "item_price_nvat")
    val itemPriceNvat:Double,

    @ColumnInfo(name = "vat")
    val vat:Double,

    @ColumnInfo(name = "item_price_wvat")
    val  itemPriceWvat:Double,

    @ColumnInfo(name = "item_total_amount")
    val itemTotalAmount: Double
)
