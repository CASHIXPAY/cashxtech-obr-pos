package com.gacoca.obr.activity.inventory

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.CategoryAdapter
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
        /**val categoryName = etCategoryName.text.toString()

        saveCategory(categoryName)
        etCategoryName.text.clear()

        val toast = Toast.makeText(this, "Category = $categoryName saved",Toast.LENGTH_SHORT)
        toast.show() **/

        addCategoryDialog()
        val toast = Toast.makeText(this, "Category saved",Toast.LENGTH_SHORT)
        toast.show()


        btDeleteCategory.setOnClickListener {

            val categoryToRemoveList = categoryAdapter.getDeleteCategoryList()
            removeCategories(categoryToRemoveList)
            categoryAdapter.deleteCategoryItem()
        }


    }


  private  fun addCategoryDialog(){

        btAddCategory.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.add_category_name,null)
            val  addCategory = dialogLayout.findViewById<EditText>(R.id.etCategoryName)

            with(builder){
                setTitle("Enter Category Name")
                setPositiveButton("Add"){dialog,which ->

                 val category =    saveCategory(addCategory.text.toString())
                    categoryAdapter.addCategoryItem(category)
                }
                setNegativeButton("Cancel"){dialog,which ->

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