package com.gacoca.obr.adapter.inventory


import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.gacoca.obr.R
import com.gacoca.obr.activity.inventory.CategoryUpdateActivity

import com.gacoca.obr.model.inventory.entities.Category
import kotlinx.android.synthetic.main.activity_inventory_category_item.view.*


class CategoryAdapter (private  val categoryItemList: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {

    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(


            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_inventory_category_item,parent,false)
        )

    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val curCategoryItem = categoryItemList[position]
        holder.itemView.apply {
            tvCategoryItem.text = curCategoryItem.categoryName
            cbSelect.isChecked = curCategoryItem.checked
            toggleStrikeThrough(tvCategoryItem,curCategoryItem.checked)
            cbSelect.setOnCheckedChangeListener{_,isChecked ->
                toggleStrikeThrough(tvCategoryItem,isChecked)
                curCategoryItem.checked = !curCategoryItem.checked
            }

            btModify.setOnClickListener{

                val intent = Intent(holder.itemView.context, CategoryUpdateActivity::class.java)

                intent.putExtra("CategoryName",curCategoryItem.categoryName)

                holder.itemView.context.startActivity(intent)
            }

        }
    }

    private fun toggleStrikeThrough(tvCategoryItem: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvCategoryItem.paintFlags = tvCategoryItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvCategoryItem.paintFlags = tvCategoryItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }



    fun addCategoryItem(category:Category){

        categoryItemList.add(category)
        notifyItemInserted(categoryItemList.size-1)
    }

     fun deleteCategoryItem(){

        categoryItemList.removeAll{
            category -> category.checked
        }

        notifyDataSetChanged()

    }

    fun getDeleteCategoryList():List<Category>{

       val categoryList =  mutableListOf<Category>()

       for(category in categoryItemList){

           if(category.checked){
               categoryList.add(category)
           }

       }

        return categoryList
    }


    override fun getItemCount(): Int {

        return categoryItemList.size
    }
}