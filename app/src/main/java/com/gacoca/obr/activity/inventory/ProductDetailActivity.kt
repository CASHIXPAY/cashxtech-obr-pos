package com.gacoca.obr.activity.inventory


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Product
import com.gacoca.obr.model.inventory.repository.InventoryRepository
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.activity_inventory_product_details.*
import kotlinx.android.synthetic.main.activity_inventory_product_details.autoCompleteCategory
import kotlinx.android.synthetic.main.activity_inventory_product_details.btScanBarcode


class ProductDetailActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_product_details)

        var productName = intent.getStringExtra("ProductName")


        val product = productName?.let { getProduct(it) }



        if (product != null) {
            edProductName.setText(product.productName)
            autoCompleteCategory.setText(product.categoryName)
            edProductPrice.setText(product.productPrice.toString())
            edBarCode.setText(product.productCode)
            edProductUnit.setText(product.productUnit)

            enableEdits(false)
        }

        val categoryList = getCategoryNames()
        val adapter =
            ArrayAdapter(this, android.R.layout.select_dialog_item, categoryList.toTypedArray())
        val category = findViewById<View>(R.id.autoCompleteCategory) as AutoCompleteTextView
        category.threshold = 1 //will start working from first character
        category.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
        category.setTextColor(Color.RED)



        btUpdate.setOnClickListener{

            if (product != null) {
                productName = edProductName.text.toString().uppercase().replace("\\s+".toRegex()," ")
                if(!productExist(productName!!)){
                    updateProduct(product)

                    enableEdits(false)

                    val  intent = Intent(this,ProductActivity::class.java)
                    startActivity(intent)
                } else {

                    val toast =
                        Toast.makeText(this, "Product $productName already exist", Toast.LENGTH_SHORT)
                    toast.show()
                }


            }
        }
        btModify.setOnClickListener{

            if(noCategoryExist()){
                val toast = Toast.makeText(this, "Please add a category first", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                enableEdits(true)
            }

        }

        btScanBarcode.setOnClickListener{

            scanCode()
        }


        }



    private fun noCategoryExist():Boolean{
        val inventoryRepo = getInventoryRepo()

        return  inventoryRepo.getCategories().isEmpty()
    }

    private fun getCategoryNames(): List<String> {

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.getCategoryNames()

    }

    private fun enableEdits(isEnable:Boolean){

        edProductName.isEnabled = isEnable
        autoCompleteCategory.isEnabled = isEnable
        edProductPrice.isEnabled = isEnable
        edBarCode.isEnabled = isEnable
        edProductUnit.isEnabled = isEnable
        btScanBarcode.isEnabled = isEnable
        btUpdate.isEnabled = isEnable
    }

    private fun  updateProduct(product: Product){

        val inventoryRepo = getInventoryRepo()

        product.productName = edProductName.text.toString().uppercase().replace("\\s+".toRegex()," ")
        product.categoryName = autoCompleteCategory.text.toString()
        product.productPrice = edProductPrice.text.toString().toDouble()
        product.productCode = edBarCode.text.toString()
        product.productUnit = edProductUnit.text.toString()

        inventoryRepo.updateProduct(product)

    }
    private fun  getProduct(productName: String):Product{

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.getProductByName(productName)

    }

    private fun productExist(productName: String): Boolean {
        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.isProductExist(productName.uppercase().replace("\\s+".toRegex()," "))

    }

    private fun getInventoryRepo(): InventoryRepository {
        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        return InventoryRepository(inventoryDao)

    }

    private fun scanCode() {

        val options = ScanOptions()

        options.setPrompt("Scan barcode")
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        options.captureActivity = CaptureActivity::class.java
        barcodeLauncher.launch(options)
    }


    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            edBarCode.setText(result.contents)
            Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                .show()
        }
    }
    }


