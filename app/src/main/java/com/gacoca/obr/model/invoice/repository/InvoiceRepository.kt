package com.gacoca.obr.model.invoice.repository

import com.gacoca.obr.model.invoice.entities.Invoice
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.dao.InvoiceDao

class InvoiceRepository(private  val invoiceDao: InvoiceDao) {

     fun addInvoice(invoiceWithItems: InvoiceWithItems){

        val invoice:Invoice = invoiceWithItems.invoice;
        invoiceDao.insertInvoice(invoice)

        val invoiceItems:List<InvoiceItem> = invoiceWithItems.invoiceItems

        for (invoiceItem:InvoiceItem in invoiceItems){
            invoiceDao.insertInvoiceItem(invoiceItem)
        }
    }

    fun getInvoices():List<Invoice>{

        return invoiceDao.getInvoices()
    }
}