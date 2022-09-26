package com.gacoca.obr.model.invoice.entities

import androidx.room.Embedded
import androidx.room.Relation


data class InvoiceWithItems(

    @Embedded val invoice: Invoice,

    @Relation(
        parentColumn = "invoice_number",
        entityColumn = "invoice_number_ref"
    )
    val invoiceItems: List<InvoiceItem>
)
