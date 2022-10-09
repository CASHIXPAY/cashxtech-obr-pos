package com.gacoca.obr.activity.invoice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.InvoiceItemAdapterIM

import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.enumeration.InvoiceType
import com.gacoca.obr.model.invoice.logic.InvoiceDetails

import com.gacoca.obr.model.invoice.repository.InvoiceRepository
import kotlinx.android.synthetic.main.activity_invoice.*



class InvoiceActivity : AppCompatActivity() {

    private lateinit var invoiceItemAdapterIM: InvoiceItemAdapterIM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val invoiceNumber = intent.getStringExtra("InvoiceNumber")

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao()

        val invoiceRepo = InvoiceRepository(invoiceDao)

        val invoiceWithItems = invoiceNumber?.let { invoiceRepo.getInvoiceWithItems(it) }

        val invoiceItemList = invoiceWithItems?.invoiceItems

        invoiceItemAdapterIM = invoiceItemList?.let { InvoiceItemAdapterIM(it) }!!
        rvInvoiceItemListIM.adapter = invoiceItemAdapterIM
        rvInvoiceItemListIM.layoutManager = LinearLayoutManager(this)

        val  invoice = invoiceWithItems.invoice

        if(invoice.invoiceType == InvoiceType.RC.toString()){
            btAnnulation.isEnabled = false
            btReduction.isEnabled = false
        }
        tvInvoiceNumberDynamic.text = invoice.invoiceNumber
        tvInvoiceTypeDynamic.text = invoice.invoiceType
        tvInvoiceDate.text = invoice.invoiceDate
        tvNumberItemsDynamic.text = invoice.invoiceTotalItems.toString()
        tvTotalPriceDynamic.text = invoice.invoiceTotalAmount.toString()

        btAnnulation.setOnClickListener {

            cancelInvoice(invoiceWithItems)

        }

        btReduction.setOnClickListener {
            val intent = Intent(this, CancelnvoicePopUpWindow::class.java)
            intent.putExtra("popuptitle", "Error")
            intent.putExtra("popuptext", "Sorry, that email address is already used!")
            intent.putExtra("popupbtn", "OK")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }

    }

    private fun cancelInvoice(invoiceWithItems: InvoiceWithItems){
        val invoiceDetails = InvoiceDetails()

        val invoiceItemList = invoiceWithItems.invoiceItems
        val cancelledInvoice = invoiceDetails.get(invoiceItemList, InvoiceType.RC)

        val invoice = invoiceWithItems.invoice
        val invoiceRef = invoice.invoiceNumber

        cancelledInvoice.invoiceRef = invoiceRef

        for (invoiceItem in invoiceItemList) {

            invoiceItem.id = 0
            invoiceItem.invoiceNumber = cancelledInvoice.invoiceNumber
        }

        val cancelledInvoiceWithItems =
            InvoiceWithItems(cancelledInvoice, invoiceItemList)

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao()

        val invoiceRepo = InvoiceRepository(invoiceDao)

        invoiceRepo.addInvoice(cancelledInvoiceWithItems)

    }
}