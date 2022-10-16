package com.gacoca.obr.activity.inventory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.CategoryAdapter
import com.gacoca.obr.adapter.InvoiceItemAdapterIM
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.repository.InventoryRepository
import kotlinx.android.synthetic.main.activity_inventory_category_main.*
import kotlinx.android.synthetic.main.activity_invoice_manager.*


class CategoryActivity : AppCompatActivity() {


    private lateinit var categoryAdapter:  CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_category_main)


        val categoryItemList  = getCategories()

        categoryAdapter = CategoryAdapter(categoryItemList.toMutableList())
        rvCategoryItem.adapter = categoryAdapter
        rvCategoryItem.layoutManager = LinearLayoutManager(this)

        btAddCategory.setOnClickListener {

            /**val categoryName = etCategoryName.text.toString()

            saveCategory(categoryName)
            etCategoryName.text.clear()

            val toast = Toast.makeText(this, "Category = $categoryName saved",Toast.LENGTH_SHORT)
            toast.show() **/

            val categoryName = "HEAVY"
           val category =  saveCategory(categoryName)
            categoryAdapter.addCategoryItem(category)
            val toast = Toast.makeText(this, "Category = $categoryName saved",Toast.LENGTH_SHORT)
            toast.show()


        }

        btDeleteCategory.setOnClickListener {

            val categoryToRemoveList = categoryAdapter.getDeleteCategoryList()
            removeCategories(categoryToRemoveList)
            categoryAdapter.deleteCategoryItem()
        }


    }


    private fun saveCategory(categoryName:String):Category{

        val category = Category(0,categoryName.uppercase())

        val inventoryRepo = getInventoryRepo()

        inventoryRepo.saveCategory(category)

        return category
    }

    private fun  getCategories():List<Category>{

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.getCategories()

    }

    private fun removeCategories(categoryList: List<Category>){

        val inventoryRepo = getInventoryRepo()

        inventoryRepo.removeCategory(categoryList)
    }


    private fun getInventoryRepo(): InventoryRepository{
        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        return InventoryRepository(inventoryDao)

    }
}