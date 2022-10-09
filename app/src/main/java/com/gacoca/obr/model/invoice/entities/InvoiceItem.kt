package com.gacoca.obr.model.invoice.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "invoice_item")
data class InvoiceItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "invoice_number_ref")
    var invoiceNumber: String?,

    @ColumnInfo(name= "item_designation")
    val itemDesignation: String?,

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
    val itemPriceWvat:Double,

    @ColumnInfo(name = "item_total_amount")
    val itemTotalAmount: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(invoiceNumber)
        parcel.writeString(itemDesignation)
        parcel.writeDouble(itemQuantity)
        parcel.writeDouble(itemPrice)
        parcel.writeDouble(itemCt)
        parcel.writeDouble(itemTl)
        parcel.writeDouble(itemPriceNvat)
        parcel.writeDouble(vat)
        parcel.writeDouble(itemPriceWvat)
        parcel.writeDouble(itemTotalAmount)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InvoiceItem> {
        override fun createFromParcel(parcel: Parcel): InvoiceItem {
            return InvoiceItem(parcel)
        }

        override fun newArray(size: Int): Array<InvoiceItem?> {
            return arrayOfNulls(size)
        }
    }
}

