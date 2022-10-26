package com.gacoca.obr.adapter.invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import kotlinx.android.synthetic.main.invoice_item_list.view.*

class InvoiceItemAdapter(private val invoiceItemList: MutableList<InvoiceItem>)
    : RecyclerView.Adapter<InvoiceItemAdapter.InvoiceItemViewHolder>() {

        class InvoiceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceItemViewHolder {
        return InvoiceItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.invoice_item_list,parent,false)
            )

    }

    fun addItem(invoiceItem: InvoiceItem){
        invoiceItemList.add(invoiceItem)
        notifyItemInserted(invoiceItemList.size-1)
    }

    private fun deleteItem(invoiceItem: InvoiceItem){

        invoiceItemList.remove(invoiceItem)
        notifyDataSetChanged();
    }

    override fun onBindViewHolder(holder: InvoiceItemViewHolder, position: Int) {

        val curInvoiceItem = invoiceItemList[position]
        holder.itemView.apply {
            tvItemDesignation.text = curInvoiceItem.itemDesignation
            tvItemQuantity.text = curInvoiceItem.itemQuantity.toString()
            tvItemPrice.text = curInvoiceItem.itemTotalAmount.toString()

            btDelete.setOnClickListener {

                deleteItem(curInvoiceItem)

            }
        }
    }

    override fun getItemCount(): Int {
        return invoiceItemList.size
    }

    fun getItemList(): List<InvoiceItem>{

        return  invoiceItemList;
    }


}