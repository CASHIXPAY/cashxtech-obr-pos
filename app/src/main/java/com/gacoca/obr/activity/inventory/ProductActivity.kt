package com.gacoca.obr.activity.inventory


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Product

import com.gacoca.obr.model.inventory.repository.InventoryRepository
import kotlinx.android.synthetic.main.activity_inventory_add_product_item.*


class ProductActivity  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_add_product_item)

        val categoryList = getCategoryNames()
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, categoryList.toTypedArray())
        val category = findViewById<View>(R.id.autoCompleteCategory) as AutoCompleteTextView
        category.threshold = 1 //will start working from first character
        category.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
        category.setTextColor(Color.RED)

        btSaveProduct.setOnClickListener {

            val productName = etProductName.text.toString()
            val categoryName:String = category.text.toString()

            val price:Double = etPrice.text.toString().toDouble()

            val barCode:String = etBarcode.text.toString()

            saveProduct(productName,categoryName,price,barCode)
        }
    }

    private fun getInventoryRepo(): InventoryRepository {
        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        return InventoryRepository(inventoryDao)

    }

    private fun saveProduct(productName:String,categoryName:String,price:Double,barCode:String){

        val product = Product(0,categoryName,productName,price, barCode ,"pcs")

        val inventoryRepo = getInventoryRepo()

        inventoryRepo.saveProduct(product)
    }

    private fun getCategoryNames():List<String>{

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.getCategoryNames()

    }



}