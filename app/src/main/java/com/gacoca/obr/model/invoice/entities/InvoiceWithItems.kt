package com.gacoca.obr.model.invoice.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.gacoca.obr.model.invoice.entities.Invoice

data class InvoiceWithItems(

    @Embedded val invoice: Invoice,

    @Relation(
        parentColumn = "invoiceNumber",
        entityColumn = "invoiceNumberRef"
    )
    val invoiceItems: List<InvoiceItem>
)
