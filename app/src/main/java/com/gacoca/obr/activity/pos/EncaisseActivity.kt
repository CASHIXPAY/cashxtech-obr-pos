package com.gacoca.obr.activity.pos

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.activity.MainActivity

import com.gacoca.obr.adapter.InvoiceItemEncaisseAdapter
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.logic.InvoiceDetails
import com.gacoca.obr.model.invoice.repository.InvoiceRepository


import kotlinx.android.synthetic.main.activity_pos_encaisse.*


class EncaisseActivity : AppCompatActivity() {

    private lateinit var invoiceItemEncaisseAdapter: InvoiceItemEncaisseAdapter

    private lateinit var invoiceDetails : InvoiceDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_encaisse)


        val invoiceItemList:List<InvoiceItem> = intent.getParcelableArrayListExtra("ItemList")!!

        invoiceItemEncaisseAdapter = InvoiceItemEncaisseAdapter(invoiceItemList as MutableList<InvoiceItem>)

        invoiceDetails = InvoiceDetails()
        rvInvoiceItemsEncaisse.adapter =  invoiceItemEncaisseAdapter
        rvInvoiceItemsEncaisse.layoutManager = LinearLayoutManager(this)


        tvItemCountValue.text = invoiceItemList.size.toString()

        var invoiceTotalAmount = 0.0;

        for(invoiceItem in invoiceItemList){

            invoiceTotalAmount += invoiceItem.itemTotalAmount;
        }
        tvItemTotalValue.text = invoiceTotalAmount.toString()

        btPay.setOnClickListener {

            val invoice = invoiceDetails.get(invoiceItemList)

            for(invoiceItem in invoiceItemList){

                invoiceItem.invoiceNumber = invoice.invoiceNumber
            }
            val appDb = PosDatabase.getDatabase(this)

            val invoiceDao = appDb.invoiceDao();

            val invoiceRepo = InvoiceRepository(invoiceDao)

            val invoiceWithItems = InvoiceWithItems(invoice,invoiceItemList)
            invoiceRepo.addInvoice(invoiceWithItems)


            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)


        }
    }
}