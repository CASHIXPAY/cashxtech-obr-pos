package com.gacoca.obr.activity.inventory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.repository.InventoryRepository
import kotlinx.android.synthetic.main.activity_inventory_add_category.*


class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_add_category)


        btAddCategory.setOnClickListener {

            val categoryName = etCategoryName.text.toString()

            saveCategory(categoryName)
            etCategoryName.text.clear()

            val toast = Toast.makeText(this, "Category = $categoryName saved",Toast.LENGTH_SHORT)
            toast.show()

        }


    }


    private fun saveCategory(categoryName:String){

        val category = Category(0,categoryName.uppercase())

        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        val inventoryRepo = InventoryRepository(inventoryDao)

        inventoryRepo.saveCategory(category)
    }
}