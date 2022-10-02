package com.gacoca.obr.activity.pos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R

import com.gacoca.obr.adapter.InvoiceItemEncaisseAdapter
import com.gacoca.obr.model.invoice.entities.InvoiceItem


import kotlinx.android.synthetic.main.activity_pos.btPay
import kotlinx.android.synthetic.main.activity_pos_encaisse.*

class EncaisseActivity : AppCompatActivity() {

    private lateinit var invoiceItemEncaisseAdapter: InvoiceItemEncaisseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_encaisse)


        var invoiceItemList:List<InvoiceItem> = ArrayList<InvoiceItem>()
        invoiceItemList= intent.getParcelableArrayListExtra("ItemList")!!

        invoiceItemEncaisseAdapter = InvoiceItemEncaisseAdapter(invoiceItemList as MutableList<InvoiceItem>)

        rvInvoiceItemsEncaisse.adapter =  invoiceItemEncaisseAdapter
        rvInvoiceItemsEncaisse.layoutManager = LinearLayoutManager(this)

        btPay.setOnClickListener {

            //    val appDb = PosDatabase.getDatabase(this)

            //   val invoiceDao = appDb.invoiceDao();

            //  val invoiceRepo = InvoiceRepository(invoiceDao)

            //   invoiceRepo.addInvoice(invoiceWithItems)

           // val invoiceWithItems = InvoiceWithItems(invoice,invoiceItemList)


        }
    }
}