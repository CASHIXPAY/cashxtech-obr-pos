package com.gacoca.obr.model.invoice.logic

import com.gacoca.obr.model.invoice.entities.Invoice
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.enumeration.InvoiceType
import java.time.LocalDateTime

class InvoiceDetails() {



    fun get( invoiceItems: List<InvoiceItem>,invoiceType: InvoiceType):Invoice{

        val  localDateTime = LocalDateTime.now();

        val invoiceNumber = getInvoiceNumber(localDateTime);

        val invoiceLocalRef = getInvoiceNumberRef(localDateTime);

        val invoiceDate = localDateTime.toLocalDate().toString();

        val tpType = "1";

        val tpName = "Chez FIDODIDO";

        val tpTIN = "44334454433";

        val tpTradeNumber = "554455";

        val tpPhoneNumber = "+257999888"

        val tpAddressCommune = "ROHERO";

        val tpAddressQuartier = "2"

        val vatTaxPayer = "0"

        val ctTaxPayer = "1"

        val ltTaxPayer = "1"

        val tpFiscalCenter = "DPMC";

        val tpActivitySector = "Alimentation";

        val tpLegalForm = "N/A";

        val paymentType = "1"

        val customerName = "N/A";

        val invoiceSignature = "40000444/ws3344455/202222/001";

        val invoiceSignatureDate = localDateTime.toString()

        val invoiceTotalItems = invoiceItems.size
        var invoiceTotalAmount = 0.0;

        for(invoiceItem in invoiceItems){

            invoiceTotalAmount += invoiceItem.itemTotalAmount;
        }



        return Invoice(0,invoiceNumber,invoiceLocalRef,invoiceDate,invoiceType.toString(),tpType,tpName,
        tpTIN,tpTradeNumber,tpPhoneNumber,tpAddressCommune,tpAddressQuartier,vatTaxPayer,ctTaxPayer,ltTaxPayer,
        tpFiscalCenter,tpActivitySector, tpLegalForm, paymentType, customerName,"" ,invoiceSignature, invoiceSignatureDate,invoiceTotalItems, invoiceTotalAmount)

    }



    private fun getInvoiceNumber(localDateTime: LocalDateTime):String{

    var invoiceNumber = localDateTime.toString()

    invoiceNumber = invoiceNumber
    .replace("-", "")
    .replace("T", "")
    .replace(":", "")
    .replace(".", "")

    return invoiceNumber
    }


    private fun getInvoiceNumberRef(localDateTime: LocalDateTime):String{

    val localDate = localDateTime.toLocalDate().toString().replace("-", "")

    val localTime = localDateTime.toLocalTime().toString().replace(":", "").replace(".", "-")

    return "$localDate-$localTime"
    }
}