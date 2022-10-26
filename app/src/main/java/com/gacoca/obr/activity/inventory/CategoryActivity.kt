package com.gacoca.obr.activity.inventory

import android.os.Bundle

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.inventory.CategoryAdapter
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.inventory.entities.Category
import com.gacoca.obr.model.inventory.repository.InventoryRepository
import kotlinx.android.synthetic.main.activity_inventory_category_main.*



class CategoryActivity : AppCompatActivity() {


    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_category_main)


        val categoryItemList  = getCategories()

        categoryAdapter = CategoryAdapter(categoryItemList.toMutableList())
        rvCategoryItem.adapter = categoryAdapter
        rvCategoryItem.layoutManager = LinearLayoutManager(this)
        addCategoryDialog()

        btDeleteCategory.setOnClickListener {

            val categoryToRemoveList = categoryAdapter.getDeleteCategoryList()

            if(categoryToRemoveList.isEmpty()){
                val toast = Toast.makeText(this, "Please select a category",Toast.LENGTH_SHORT)
                toast.show()
            }else{
                removeCategories(categoryToRemoveList)
                categoryAdapter.deleteCategoryItem()
            }

        }


    }


  private  fun addCategoryDialog(){

        btAddCategory.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.activity_inventory_add_category_name,null)
            val  addCategory = dialogLayout.findViewById<EditText>(R.id.etCategoryName)

            with(builder){
                setTitle("Enter Category Name")
                setPositiveButton("Add"){ _, _ ->

                 val category =    saveCategory(addCategory.text.toString())
                    categoryAdapter.addCategoryItem(category)
                }
                setNegativeButton("Cancel"){_,_ ->

                }

                setView(dialogLayout)
                show()
            }

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