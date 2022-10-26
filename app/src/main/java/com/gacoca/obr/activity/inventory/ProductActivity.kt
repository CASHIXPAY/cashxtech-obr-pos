package com.gacoca.obr.activity.inventory

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.inventory.ProductAdapter
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Category

import com.gacoca.obr.model.inventory.entities.Product

import com.gacoca.obr.model.inventory.repository.InventoryRepository

import kotlinx.android.synthetic.main.activity_inventory_product_main.*


class ProductActivity  : AppCompatActivity(){

    private lateinit var  productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_product_main)

        val productItemList = getProducts()

        productAdapter = ProductAdapter(productItemList.toMutableList())
        rvProductItem.adapter = productAdapter
        rvProductItem.layoutManager = LinearLayoutManager(this)

        btAddProduct.setOnClickListener {

            val  intent = Intent(this,AddProductActivity::class.java)
            startActivity(intent)
        }

        btDeleteProduct.setOnClickListener {

            val productToRemoveList = productAdapter.getDeleteProductList()

            println(productToRemoveList.size)
            if(productToRemoveList.isEmpty()){
                val toast = Toast.makeText(this, "Please select a product", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                removeProduct(productToRemoveList)
                productAdapter.deleteProductItem()
            }
        }


        }


    private fun removeProduct(productList: List<Product>){

        val inventoryRepo = getInventoryRepo()

        inventoryRepo.removeProduct(productList)
    }

    private fun  getProducts():List<Product>{

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.listProducts()

    }
    private fun getInventoryRepo(): InventoryRepository{
        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        return InventoryRepository(inventoryDao)

    }

}