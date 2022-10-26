package com.gacoca.obr.adapter.invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import kotlinx.android.synthetic.main.invoice_item_list.view.*

class InvoiceItemEncaisseAdapter(private val invoiceItemList: MutableList<InvoiceItem>) : RecyclerView.Adapter<InvoiceItemEncaisseAdapter.InvoiceItemViewHolder>() {

    class InvoiceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceItemViewHolder {
        return InvoiceItemViewHolder(


            LayoutInflater.from(parent.context).inflate(
                R.layout.invoice_item_list_encaisse,parent,false)
        )

    }




    override fun onBindViewHolder(holder: InvoiceItemViewHolder, position: Int) {

        val curInvoiceItem = invoiceItemList[position]
        holder.itemView.apply {
            tvItemDesignation.text = curInvoiceItem.itemDesignation
            tvItemQuantity.text = curInvoiceItem.itemQuantity.toString()
            tvItemPrice.text = curInvoiceItem.itemTotalAmount.toString()

        }
    }

    override fun getItemCount(): Int {
        return invoiceItemList.size
    }

    fun getItemList(): List<InvoiceItem>{

        return  invoiceItemList;
    }


}