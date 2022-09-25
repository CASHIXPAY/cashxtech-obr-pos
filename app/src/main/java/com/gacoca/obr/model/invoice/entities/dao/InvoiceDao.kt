package com.gacoca.obr.model.invoice.entities.dao

import androidx.room.*
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems

@Dao
interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInvoice(invoice: InvoiceWithItems)

    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getUsersWithPlaylists(): List<InvoiceWithItems>
}