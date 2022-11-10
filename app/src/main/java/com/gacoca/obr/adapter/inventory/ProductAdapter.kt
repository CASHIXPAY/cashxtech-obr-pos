package com.gacoca.obr.adapter.inventory

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.activity.inventory.ProductDetailActivity

import com.gacoca.obr.model.inventory.entities.Product

import kotlinx.android.synthetic.main.activity_inventory_category_item.view.cbSelect
import kotlinx.android.synthetic.main.activity_inventory_product_item.view.*
import kotlinx.android.synthetic.main.activity_inventory_product_item.view.btDetails


class ProductAdapter (private  val productItemList: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductItemViewHolder>() {


    class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_inventory_product_item, parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val curProductItem = productItemList[position]
        holder.itemView.apply {
            tvProductItem.text = curProductItem.productName
            cbSelect.isChecked = curProductItem.checked
            toggleStrikeThrough(tvProductItem,curProductItem.checked)
            cbSelect.setOnCheckedChangeListener{_,isChecked ->
                toggleStrikeThrough(tvProductItem,isChecked)
                curProductItem.checked = !curProductItem.checked


            }

            btDetails.setOnClickListener {

                val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java)

                 intent.putExtra("ProductName",curProductItem.productName)

                holder.itemView.context.startActivity(intent)
            }

        }
    }

    private fun toggleStrikeThrough(tvProductItem: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvProductItem.paintFlags = tvProductItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvProductItem.paintFlags = tvProductItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun deleteProductItem(){

        productItemList.removeAll{
                product -> product.checked
        }

        notifyDataSetChanged()

    }
    fun getDeleteProductList():List<Product>{

        val productList =  mutableListOf<Product>()

        for(product in productItemList){

            if(product.checked){
                productList.add(product)
            }

        }

        return productList
    }
    override fun getItemCount(): Int {

        return productItemList.size
    }

}