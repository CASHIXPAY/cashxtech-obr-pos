package com.gacoca.obr.activity.invoice

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.gacoca.obr.R
import com.gacoca.obr.database.PosDatabase
import com.gacoca.obr.model.invoice.entities.InvoiceWithItems
import com.gacoca.obr.model.invoice.enumeration.InvoiceType
import com.gacoca.obr.model.invoice.logic.InvoiceDetails
import com.gacoca.obr.model.invoice.repository.InvoiceRepository
import kotlinx.android.synthetic.main.activity_cancel_invoice_pop_up_window.*

class CancelInvoicePopUpWindow : AppCompatActivity() {

    private var popupTitle = ""
    private var popupText = ""
    private var darkStatusBar = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_cancel_invoice_pop_up_window)

        val bundle = intent.extras
        popupTitle = bundle?.getString("popuptitle", "Title") ?: ""
        popupText = bundle?.getString("popuptext", "Text") ?: ""
        val invoiceNumber = bundle?.getString("InvoiceNumber", "InvoiceNumber") ?: ""
        darkStatusBar = bundle?.getBoolean("darkstatusbar", false) ?: false

        // Set the data
        popup_window_title.text = popupTitle
        popup_window_text.text = popupText


        // Fade animation for the background of Popup Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Fade animation for the Popup Window
        popup_window_view_with_border.alpha = 0f
        popup_window_view_with_border.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Close the Popup Window when you press the button
        popup_window_bt_cancel.setOnClickListener {
            onBackPressed()
        }

        popup_window_bt_ok.setOnClickListener {

            cancelInvoice(invoiceNumber)
            onBackPressed()
        }
        // ...
    }

    override fun onBackPressed() {
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        popup_window_view_with_border.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }

    private fun cancelInvoice(invoiceNumber: String){

        val appDb = PosDatabase.getDatabase(this)

        val invoiceDao = appDb.invoiceDao()

        val invoiceRepo = InvoiceRepository(invoiceDao)

        val invoiceWithItems = invoiceNumber.let { invoiceRepo.getInvoiceWithItems(it) }

        val invoiceDetails = InvoiceDetails()

        val invoiceItemList = invoiceWithItems.invoiceItems
        val cancelledInvoice = invoiceDetails.get(invoiceItemList, InvoiceType.RC)

        val invoice = invoiceWithItems.invoice
        val invoiceRef = invoice.invoiceNumber

        cancelledInvoice.invoiceRef = invoiceRef

        for (invoiceItem in invoiceItemList) {

            invoiceItem.id = 0
            invoiceItem.invoiceNumber = cancelledInvoice.invoiceNumber
        }

        val cancelledInvoiceWithItems =
            InvoiceWithItems(cancelledInvoice, invoiceItemList)


        invoiceRepo.addInvoice(cancelledInvoiceWithItems)

    }
}