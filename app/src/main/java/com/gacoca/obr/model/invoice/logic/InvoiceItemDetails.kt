package com.gacoca.obr.model.invoice.logic

import com.gacoca.obr.model.invoice.entities.InvoiceItem
import java.time.LocalDateTime
import kotlin.math.roundToInt

class InvoiceItemDetails () {


    fun get(itemDesignation:String, itemQuantity:Double, itemUnitPrice:Double ): InvoiceItem{

        val itemPrice = itemUnitPrice * itemQuantity

        val itemCt = getItemCt(itemPrice)

        val itemTl = getItemTl(itemPrice)

        val itemPriceNvat = itemPrice + itemCt;

        val vat = getVat(itemPriceNvat)

        val itemPriceWvat = itemPriceNvat + vat

        val itemTotalAmount = itemPriceWvat + itemTl

        return InvoiceItem(0,null,itemDesignation,itemQuantity,itemUnitPrice,itemCt,itemTl,itemPriceNvat,vat,itemPriceWvat,itemTotalAmount)
    }


    private fun  getItemCt(itemPrice:Double):Double{

        return roundPrice((itemPrice * 1.5)/100)
    }

    private fun  getItemTl(itemPrice: Double):Double{

        return roundPrice((itemPrice * 2)/100)
    }

    private fun getVat(itemPriceNvat:Double):Double{

        return roundPrice((itemPriceNvat * 8)/100)
    }


    private fun roundPrice(price:Double):Double{

        return (price * 100.0).roundToInt() / 100.0

    }



}