package com.gacoca.obr.activity.inventory


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.repository.InventoryRepository
import kotlinx.android.synthetic.main.activity_inventory_category_modify_item.*



class CategoryUpdateActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_category_modify_item)

        val categoryName = intent.getStringExtra("CategoryName")


        val category = categoryName?.let { getCategory(it) }

        etCurrentCategory.isEnabled = false
        etCurrentCategory.setText(categoryName)

        btUpdate.setOnClickListener {

            if (category != null) {

                val newCategoryName = etNewCategory.text.toString().uppercase().replace("\\s+".toRegex()," ")
                if(!categoryExist(newCategoryName)){
                    category.categoryName = newCategoryName
                    updateCategory(category)

                    val  intent = Intent(this,CategoryActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Category ${categoryName.uppercase().replace("\\s+".toRegex()," ")} already exist", Toast.LENGTH_SHORT).show()
                    //    Toast.makeText(this, "Category $categoryName already exist", Toast.LENGTH_SHORT).show()
                }


            }


        }


    }


    private fun categoryExist(categoryName: String):Boolean{
        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.isCategoryExist(categoryName)
    }


    private fun  updateCategory(category: Category){

        val inventoryRepo = getInventoryRepo()
        inventoryRepo.updateCategory(category)

    }

    private fun  getCategory(categoryName: String): Category {

        val inventoryRepo = getInventoryRepo()

        return inventoryRepo.getCategoryByName(categoryName)

    }
    private fun getInventoryRepo(): InventoryRepository {
        val appDb = PosDatabase.getDatabase(this)

        val inventoryDao = appDb.inventoryDao()

        return InventoryRepository(inventoryDao)

    }


}