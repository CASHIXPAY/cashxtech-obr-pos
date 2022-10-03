package com.gacoca.obr.activity.invoice


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R

import com.gacoca.obr.adapter.SavedInvoiceAdapter
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.invoice.entities.Invoice

import com.gacoca.obr.model.invoice.repository.InvoiceRepository
import kotlinx.android.synthetic.main.activity_invoice_manager.*


class InvoiceManagerActivity  : AppCompatActivity() {

    private lateinit var savedInvoiceAdapter: SavedInvoiceAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_manager)

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao();

        val invoiceRepo = InvoiceRepository(invoiceDao)

        val invoiceList = invoiceRepo.getInvoices()

        println(invoiceList.size)
        savedInvoiceAdapter = SavedInvoiceAdapter(invoiceList as MutableList<Invoice>)
        rvInvoicesList.adapter = savedInvoiceAdapter
        rvInvoicesList.layoutManager = LinearLayoutManager(this)

    }
}