package com.gacoca.obr.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gacoca.obr.R
import com.gacoca.obr.activity.pos.PosActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btCaisse: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        btCaisse.setOnClickListener {
            val  intent = Intent(this, PosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView(){

        btCaisse = findViewById(R.id.btCaisse)
    }
}