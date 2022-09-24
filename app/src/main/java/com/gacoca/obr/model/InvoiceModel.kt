package com.gacoca.obr.model

data class InvoiceModel(

    var invoiceNumber: String = "",
    var invoiceDate: String = "",
    var invoiceType: String = "",
    var tpType: Int = 1,
    var tpName: String = "",
    var tpTIN:String = "",
    var tpTradeNumber:String = "",
    var tpPhoneNumber: String = "",
    var tpAddressCommune:String = "",
    var tpAddressQuartier:String = "",
    var vatTaxpayer:Int = 0,
    var ctTaxpayer:String = "",
    var ltTaxpayer:String = "",
    var tpFiscalCenter:String = "",
    var tpActivitySector:String = "",
    var tpLegalForm:String = "",
    var paymentType:String = "",
    var customerName:String = "",
    var invoiceSignature:String = "",
    var invoiceSignatureDate:String = "",
    var itemDesignation:String = "",
    var itemQuantity:Double = 0.0,
    var itemPrice:Double = 0.0,
    var itemCt:Double = 0.0,
    var itemTl:Double = 0.0,
    var itemPriceNvat:Double = 0.0,
    var vat:Double = 0.0,
    var itemPriceWvat:Double = 0.0,
    var itemTotalAmount:Double = 0.0,




)
