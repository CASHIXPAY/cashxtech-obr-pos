package com.gacoca.obr.activity.inventory

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.activity_inventory_main.*


class InventoryActivity : AppCompatActivity() {


    companion object {
        const val RESULT = "RESULT"
    }

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