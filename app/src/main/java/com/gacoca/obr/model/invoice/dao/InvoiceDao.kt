package com.gacoca.obr.model.invoice.dao

import androidx.room.*
import com.gacoca.obr.model.invoice.entities.Invoice
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems

@Dao
interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertInvoice(invoice: Invoice)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertInvoiceItem(invoiceItem: InvoiceItem)

    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getInvoiceWithItems(): List<InvoiceWithItems>

    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getInvoices(): List<Invoice>
}