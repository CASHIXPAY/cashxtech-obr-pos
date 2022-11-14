package com.gacoca.obr.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gacoca.obr.R
import com.gacoca.obr.activity.inventory.InventoryActivity
import com.gacoca.obr.activity.invoice.InvoiceManagerActivity
import com.gacoca.obr.activity.pos.PosActivity
import com.gacoca.obr.activity.shopconfig.ShopConfigActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var btCaisse: Button
    private lateinit var btInvoiceManager:Button
    private lateinit var btInventory: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        btCaisse.setOnClickListener {
            val  intent = Intent(this, PosActivity::class.java)
            startActivity(intent)
        }

        btInvoiceManager.setOnClickListener {
            val intent = Intent(this, InvoiceManagerActivity::class.java)
            startActivity(intent)
        }

        btInventory.setOnClickListener {

            val intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }

        btParametre.setOnClickListener {

            val intent = Intent(this, ShopConfigActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initView(){

        btCaisse = findViewById(R.id.btCaisse)
        btInvoiceManager = findViewById(R.id.btInvoiceManager)
        btInventory = findViewById(R.id.btInventory)
    }
}