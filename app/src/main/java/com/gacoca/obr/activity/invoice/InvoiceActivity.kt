package com.gacoca.obr.activity.invoice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacoca.obr.R
import com.gacoca.obr.adapter.InvoiceItemAdapterIM

import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.enumeration.InvoiceType
import com.gacoca.obr.model.invoice.logic.InvoiceDetails

import com.gacoca.obr.model.invoice.repository.InvoiceRepository
import kotlinx.android.synthetic.main.activity_invoice.*



class InvoiceActivity : AppCompatActivity() {

    private lateinit var invoiceItemAdapterIM: InvoiceItemAdapterIM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val invoiceNumber = intent.getStringExtra("InvoiceNumber")

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao()

        val invoiceRepo = InvoiceRepository(invoiceDao)

        val invoiceWithItems = invoiceNumber?.let { invoiceRepo.getInvoiceWithItems(it) }

        val invoiceItemList = invoiceWithItems?.invoiceItems

        invoiceItemAdapterIM = invoiceItemList?.let { InvoiceItemAdapterIM(it) }!!
        rvInvoiceItemListIM.adapter = invoiceItemAdapterIM
        rvInvoiceItemListIM.layoutManager = LinearLayoutManager(this)

        val  invoice = invoiceWithItems.invoice


        tvInvoiceNumberDynamic.text = invoice.invoiceNumber
        tvInvoiceTypeDynamic.text = invoice.invoiceType
        tvInvoiceDate.text = invoice.invoiceDate
        tvNumberItemsDynamic.text = invoice.invoiceTotalItems.toString()
        tvTotalPriceDynamic.text = invoice.invoiceTotalAmount.toString()

        var hasCancelledInvoice = false

        if(invoice.invoiceType == InvoiceType.RC.toString()){
            btAnnulation.isEnabled = false
            btReduction.isEnabled = false
        }else if(invoice.invoiceType == InvoiceType.FN.toString()){

            hasCancelledInvoice = invoiceRepo.isCancelledInvoiceExist(invoice.invoiceNumber)
        }


        btAnnulation.setOnClickListener {

            if(!hasCancelledInvoice){

                val intent = Intent(this, CancelInvoicePopUpWindow::class.java)
                intent.putExtra("popuptitle", "Annulation Facture")
                intent.putExtra("popuptext", "Etes vous sur de vouloir annuler cette facture")
                intent.putExtra("InvoiceNumber",invoiceNumber)
                intent.putExtra("darkstatusbar", false)
                startActivity(intent)
             //   cancelInvoice(invoiceWithItems)
            } else{

                val intent = Intent(this, CancelledInvoicePopUpWindow::class.java)
                intent.putExtra("popuptitle", "Erreur")
                intent.putExtra("popuptext", "Cette facture est deja annule!")
                intent.putExtra("popupbtn", "OK")
                intent.putExtra("darkstatusbar", false)
                startActivity(intent)
            }


        }

    }

}