package com.gacoca.obr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    private lateinit var btCaisse: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        btCaisse.setOnClickListener {
            val  intent = Intent(this,PosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView(){

        btCaisse = findViewById(R.id.btCaisse)
    }
}