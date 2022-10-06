package com.gacoca.obr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.model.invoice.entities.Invoice
import kotlinx.android.synthetic.main.header_invoice_saved_list.view.*
import kotlinx.android.synthetic.main.invoice_saved_list.view.*

class SavedInvoiceAdapter (private val groupedInvoicedMap: LinkedHashMap<String,List<Invoice>>) : RecyclerView.Adapter<SavedInvoiceAdapter.InvoiceViewHolder>() {

    class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        return InvoiceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.header_invoice_saved_list,parent,false)
        )

    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {

        val curHeader = groupedInvoicedMap.keys.toMutableList()[position]
        val curInvoice = groupedInvoicedMap[curHeader]
        holder.itemView.apply {
            tvHeader.text = curHeader
            val savedInvoicedDetailsAdapter = curInvoice?.let { SavedInvoicedDetailsAdapter(it.toMutableList()) }
            rvInvoiceItemList.layoutManager = LinearLayoutManager(context)
            rvInvoiceItemList.adapter = savedInvoicedDetailsAdapter

        }
    }

    fun bind(invoiceList: List<Invoice>){



    }

    override fun getItemCount(): Int {
        return groupedInvoicedMap.size
    }




}