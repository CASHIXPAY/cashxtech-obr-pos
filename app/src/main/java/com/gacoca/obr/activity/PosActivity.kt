package com.gacoca.obr.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.InvoiceItemAdapter
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import kotlinx.android.synthetic.main.activity_pos.*

class PosActivity :AppCompatActivity() {

    private lateinit var invoiceItemAdapter: InvoiceItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos)

        invoiceItemAdapter = InvoiceItemAdapter(mutableListOf())
        rvInvoiceItems.adapter = invoiceItemAdapter
        rvInvoiceItems.layoutManager = LinearLayoutManager(this)

        btAdd.setOnClickListener {
            val itemDesignation = etItemDesignation.text.toString()
            val itemQuantity = etItemQuantity.text.toString()
            val itemPrice = etItemPrice.text.toString()

            val invoiceItem = InvoiceItem(1,"test",itemDesignation,itemQuantity,itemPrice)
            invoiceItemAdapter.addItem(invoiceItem)
            etItemDesignation.text.clear()
            etItemQuantity.text.clear()
            etItemPrice.text.clear()

        }
    }
}