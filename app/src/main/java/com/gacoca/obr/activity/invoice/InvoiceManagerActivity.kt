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

        val invoiceDateList = invoiceRepo.getLatestInvoiceDates()
        val invoiceList = invoiceRepo.getLatestInvoices()

        val groupedInvoicedMap = getGroupedInvoiceByDate(invoiceDateList,invoiceList)

        savedInvoiceAdapter = SavedInvoiceAdapter(groupedInvoicedMap)
        rvInvoicesList.adapter = savedInvoiceAdapter
        rvInvoicesList.layoutManager = LinearLayoutManager(this)

    }


    private fun getGroupedInvoiceByDate(invoiceDateList:List<String>,invoiceList:List<Invoice>):LinkedHashMap<String, List<Invoice>>{

        val hashMap : LinkedHashMap<String, List<Invoice>> = LinkedHashMap ()
        for (date: String in invoiceDateList){

            hashMap[date] = getInvoiceListByDate(date,invoiceList)

        }

        return hashMap
    }


    private fun getInvoiceListByDate(invoiceDate:String,invoiceList: List<Invoice>):List<Invoice>{

       val invoiceByDateList: MutableList<Invoice> = mutableListOf()

       for(invoice: Invoice in invoiceList){

           if(invoice.invoiceDate == invoiceDate){
               invoiceByDateList.add(invoice)
           }
       }

        return invoiceByDateList
    }
}