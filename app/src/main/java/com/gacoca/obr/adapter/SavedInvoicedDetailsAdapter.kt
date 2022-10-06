package com.gacoca.obr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.model.invoice.entities.Invoice
import kotlinx.android.synthetic.main.invoice_saved_list.view.*

class SavedInvoicedDetailsAdapter (private val invoiceList: MutableList<Invoice>) : RecyclerView.Adapter<SavedInvoicedDetailsAdapter.InvoiceViewHolder>() {

    class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        return InvoiceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.invoice_saved_list,parent,false)
        )

    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {

        val curInvoice = invoiceList[position]
        holder.itemView.apply {
            tvInvoiceNumber.text = curInvoice.invoiceLocalRef
            tvItemsPrice.text = curInvoice.invoiceTotalAmount.toString()
            tvItemsTotal.text = curInvoice.invoiceTotalItems.toString()

        }
    }

    override fun getItemCount(): Int {
        return invoiceList.size
    }
}