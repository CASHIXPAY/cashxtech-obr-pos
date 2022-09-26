package com.gacoca.obr.model.invoice.entities.repository

import com.gacoca.obr.model.invoice.entities.Invoice
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.entities.dao.InvoiceDao

class InvoiceRepository(private  val invoiceDao: InvoiceDao) {

    suspend fun addInvoice(invoiceWithItems: InvoiceWithItems){

        val invoice:Invoice = invoiceWithItems.invoice;
        invoiceDao.insertInvoice(invoice)

        val invoiceItems:List<InvoiceItem> = invoiceWithItems.invoiceItems

        for (invoiceItem:InvoiceItem in invoiceItems){
            invoiceDao.insertInvoiceItem(invoiceItem)
        }
    }
}