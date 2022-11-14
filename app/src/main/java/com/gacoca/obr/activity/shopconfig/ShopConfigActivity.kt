package com.gacoca.obr.activity.shopconfig


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import kotlinx.android.synthetic.main.activity_shop_config_main.*


class ShopConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_config_main)

        btTaxConfig.setOnClickListener {

            val intent = Intent(this, TaxConfigActivity::class.java)
            startActivity(intent)
        }
    }
}