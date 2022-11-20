package com.gacoca.obr.activity.shopconfig

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.shopconfig.entities.TaxConfig
import com.gacoca.obr.model.shopconfig.repository.ShopConfigRepository
import kotlinx.android.synthetic.main.activity_shop_config_tax.*

class TaxConfigActivity : AppCompatActivity() {

    private lateinit var taxConfig: TaxConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_config_tax)

        if (isConfigured()) {
            taxConfig = prepopulate()
        }


        swVat.setOnCheckedChangeListener { _, isChecked ->
            edVatPercentage.text.clear()
            edVatPercentage.isEnabled = isChecked
        }
        btSave.setOnClickListener {

            val taxIdNumber = edTaxIdNumber.text.toString()
            val registryNumber = edRegistryNumber.text.toString()
            val fiscalCenter = edFiscalCenter.text.toString()
            val subjectVat = swVat.isChecked

            var vatPercentage = 0.0

            if (subjectVat) {

                vatPercentage = edVatPercentage.text.toString().toDouble()
            }

            if (isConfigured()) {

                taxConfig.taxIdNumber = taxIdNumber
                taxConfig.registryNumber = registryNumber
                taxConfig.fiscalCenter = fiscalCenter
                taxConfig.subjectVat = subjectVat
                taxConfig.vatPercentage = vatPercentage


                updateTaxConfig(taxConfig)
                Toast.makeText(this, "Updated tax configuration", Toast.LENGTH_SHORT).show()
            } else {

                taxConfig = TaxConfig(
                    0,
                    taxIdNumber,
                    registryNumber,
                    fiscalCenter,
                    subjectVat,
                    vatPercentage
                )

                saveTaxConfig(taxConfig)
                Toast.makeText(this, "Saved tax configuration", Toast.LENGTH_SHORT).show()
            }


        }

    }


    private fun isConfigured(): Boolean {
        val shopConfigRepo = getShopConfigRepo()

        return shopConfigRepo.isTaxConfigExist()

    }

    private fun prepopulate(): TaxConfig {

        val shopConfigRepo = getShopConfigRepo()

        val taxConfig = shopConfigRepo.getTaxConfig()

        edTaxIdNumber.setText(taxConfig.taxIdNumber)
        edRegistryNumber.setText(taxConfig.registryNumber)
        edFiscalCenter.setText(taxConfig.fiscalCenter)
        swVat.isChecked = taxConfig.subjectVat

        if (taxConfig.subjectVat) {
            edVatPercentage.setText(taxConfig.vatPercentage.toString())
        }

        return taxConfig
    }


    private fun saveTaxConfig(taxConfig: TaxConfig) {

        val shopConfigRepo = getShopConfigRepo()

        shopConfigRepo.saveTaxConfig(taxConfig)
    }

    private fun updateTaxConfig(taxConfig: TaxConfig) {

        val shopConfigRepo = getShopConfigRepo()

        shopConfigRepo.updateTaxConfig(taxConfig)
    }

    private fun getShopConfigRepo(): ShopConfigRepository {
        val appDb = PosDatabase.getDatabase(this)

        val shopConfigDao = appDb.shopConfigDao()

        return ShopConfigRepository(shopConfigDao)

    }
}