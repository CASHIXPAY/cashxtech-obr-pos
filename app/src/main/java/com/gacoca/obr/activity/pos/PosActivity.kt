package com.gacoca.obr.activity.pos


import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.InvoiceItemAdapter

import com.gacoca.obr.model.invoice.logic.InvoiceDetails
import com.gacoca.obr.model.invoice.logic.InvoiceItemDetails

import kotlinx.android.synthetic.main.activity_pos.*


class PosActivity :AppCompatActivity() {

    private lateinit var invoiceItemAdapter: InvoiceItemAdapter

    private lateinit var invoiceItemDetails: InvoiceItemDetails

    private lateinit var invoiceDetails :InvoiceDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos)

        invoiceItemAdapter = InvoiceItemAdapter(mutableListOf())
        invoiceItemDetails = InvoiceItemDetails()
        invoiceDetails = InvoiceDetails()
        rvInvoiceItems.adapter = invoiceItemAdapter
        rvInvoiceItems.layoutManager = LinearLayoutManager(this)

        btAdd.setOnClickListener {
            val itemDesignation = etItemDesignation.text.toString()
            val itemQuantity = etItemQuantity.text.toString().toDouble()
            val itemPrice = etItemPrice.text.toString().toDouble()


            val invoiceItem = invoiceItemDetails.get(itemDesignation,itemQuantity,itemPrice)
            invoiceItemAdapter.addItem(invoiceItem)
            etItemDesignation.text.clear()
            etItemQuantity.text.clear()
            etItemPrice.text.clear()

        }

        btRegisterItems.setOnClickListener {

          val invoiceItemList = invoiceItemAdapter.getItemList()

            val intent = Intent(this,EncaisseActivity::class.java)


            intent.putParcelableArrayListExtra("ItemList",invoiceItemList.toMutableList() as  java.util.ArrayList <out Parcelable> )

            startActivity(intent)


        }
    }

}