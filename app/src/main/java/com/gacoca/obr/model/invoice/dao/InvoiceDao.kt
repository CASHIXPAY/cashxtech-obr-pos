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
    fun getInvoicesWithItems(): List<InvoiceWithItems>

    @Transaction
    @Query("SELECT * FROM Invoice WHERE invoice_number = :invoiceNumber")
    fun getInvoiceWithItems(invoiceNumber:String): InvoiceWithItems

    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getInvoices(): List<Invoice>

    @Transaction
    @Query("SELECT invoice_date FROM Invoice GROUP BY invoice_date ORDER BY invoice_number DESC LIMIT 50")
    fun getLatestInvoiceDates(): List<String>

    @Transaction
    @Query("SELECT * FROM Invoice ORDER BY invoice_number DESC LIMIT 50")
    fun getLatestInvoices():List<Invoice>


    @Query("SELECT EXISTS(SELECT * FROM invoice WHERE invoice_ref = :invoiceRef)")
    fun isCancelledInvoiceExist(invoiceRef : String) : Boolean
}