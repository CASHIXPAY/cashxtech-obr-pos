package com.gacoca.obr.activity.inventory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.gacoca.obr.R
import kotlinx.android.synthetic.main.activity_inventory_main.*


class InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_main)


        btAddCategory.setOnClickListener {

            val  intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)
        }

        btAddProduct.setOnClickListener {

            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
    }
}