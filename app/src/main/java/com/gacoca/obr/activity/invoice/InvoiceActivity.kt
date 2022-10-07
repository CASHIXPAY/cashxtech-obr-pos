package com.gacoca.obr.activity.invoice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.InvoiceItemAdapterIM

import com.gacoca.obr.database.PosDatabase

import com.gacoca.obr.model.invoice.repository.InvoiceRepository
import kotlinx.android.synthetic.main.activity_invoice.*



class InvoiceActivity : AppCompatActivity() {

    private lateinit var invoiceItemAdapterIM: InvoiceItemAdapterIM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val invoiceNumber = intent.getStringExtra("InvoiceNumber")

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao();

        val invoiceRepo = InvoiceRepository(invoiceDao)

        val invoiceWithItems = invoiceNumber?.let { invoiceRepo.getInvoiceWithItems(it) }

        val invoiceItemList = invoiceWithItems?.invoiceItems

        invoiceItemAdapterIM = invoiceItemList?.let { InvoiceItemAdapterIM(it) }!!
        rvInvoiceItemListIM.adapter = invoiceItemAdapterIM
        rvInvoiceItemListIM.layoutManager = LinearLayoutManager(this)

        val  invoice = invoiceWithItems.invoice
        tvInvoiceNumberDynamic.text = invoice.invoiceNumber
        tvInvoiceTypeDynamic.text = invoice.invoiceType
        tvInvoiceDate.text = invoice.invoiceDate
        tvNumberItemsDynamic.text = invoice.invoiceTotalItems.toString()
        tvTotalPriceDynamic.text = invoice.invoiceTotalAmount.toString()
    }
}