package com.gacoca.obr.activity.shopconfig


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.shopconfig.entities.Shop


import com.gacoca.obr.model.shopconfig.repository.ShopConfigRepository
import kotlinx.android.synthetic.main.activity_shop_config_details.*
import kotlinx.android.synthetic.main.activity_shop_config_details.btSave


class ShopDetailsActivity : AppCompatActivity() {

    private val sharedPrefFile = "shopConfigSharedPreference"

    private val defaultConfigValue: String = "default_shop_is_configured_value"

    private val configValue: String = "is_shop_configured_value"

    private lateinit var shop: Shop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_config_details)


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        with(sharedPreferences.edit()) {
            putBoolean(defaultConfigValue, false)
            apply()
        }
        if (isConfigured()) {
            shop = prepopulate()
        }
        btSave.setOnClickListener {

            val shopName = edShopName.text.toString()
            val phoneNumber = edPhoneNumber.text.toString()
            val email = edEmailAddress.text.toString()
            val address = edAddress.text.toString()

            if (isConfigured()) {

                shop.name = shopName
                shop.phoneNumber = phoneNumber
                shop.email = email
                shop.address = address

                updateShopDetails(shop)

                Toast.makeText(this, "Updated shop details", Toast.LENGTH_SHORT).show()
            } else {

                shop = Shop(
                    0, shopName, phoneNumber, email, address
                )

                saveShopDetails(shop)
                with(sharedPreferences.edit()) {
                    putBoolean(configValue, true)
                    apply()
                }
                Toast.makeText(this, "Saved shop details", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isConfigured(): Boolean {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getBoolean(configValue, false)

    }

    private fun prepopulate(): Shop {

        val shopConfigRepo = getShopConfigRepo()

        val shop = shopConfigRepo.getShopDetails()

        edShopName.setText(shop.name)
        edPhoneNumber.setText(shop.phoneNumber)
        edEmailAddress.setText(shop.email)
        edAddress.setText(shop.address)

        return shop
    }

    private fun saveShopDetails(shop: Shop) {

        val shopConfigRepo = getShopConfigRepo()

        shopConfigRepo.saveShopDetails(shop)
    }

    private fun updateShopDetails(shop: Shop) {

        val shopConfigRepo = getShopConfigRepo()

        shopConfigRepo.updateShopDetails(shop)
    }

    private fun getShopConfigRepo(): ShopConfigRepository {
        val appDb = PosDatabase.getDatabase(this)

        val shopConfigDao = appDb.shopConfigDao()

        return ShopConfigRepository(shopConfigDao)

    }

}