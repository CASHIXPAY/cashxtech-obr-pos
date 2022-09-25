package com.gacoca.obr.model.invoice.entities.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems

@Dao
interface InvoiceDao {

    @Transaction
    @Query("SELECT * FROM Invoice")
    fun getUsersWithPlaylists(): List<InvoiceWithItems>
}