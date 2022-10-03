package com.gacoca.obr.model.invoice.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "invoice")
data class Invoice(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "invoice_number")
    val invoiceNumber: String,

    @ColumnInfo(name= "invoice_local_ref")
    val invoiceLocalRef:String,

    @ColumnInfo(name = "invoice_date")
    val invoiceDate: String,

    @ColumnInfo(name = "invoice_type")
    val invoiceType: String,

    @ColumnInfo(name = "tp_type")
    val tpType: String,

    @ColumnInfo(name = "tp_name")
    val tpName:String,

    @ColumnInfo(name = "tp_tin")
    val tpTIN:String,

    @ColumnInfo(name = "tp_trade_number")
    val tpTradeNumber: String,

    @ColumnInfo(name = "tp_phone_number")
    val tpPhoneNumber: String,

    @ColumnInfo(name = "tp_address_commune")
    val tpAddressCommune: String,

    @ColumnInfo(name = "tp_adress_quartier")
    val tpAddressQuartier: String,

    @ColumnInfo(name = "vat_tax_payer")
    val vatTaxPayer: String,

    @ColumnInfo(name = "ct_tax_payer")
    val ctTaxPayer: String,

    @ColumnInfo(name = "lt_tax_payer")
    val ltTaxPayer: String,

    @ColumnInfo(name = "tp_fiscal_center")
    val tpFiscalCenter: String,

    @ColumnInfo(name = "tp_activity_sector")
    val tpActivitySector:String,

    @ColumnInfo(name = "tp_legal_form")
    val tpLegalForm:String,

    @ColumnInfo(name = "payment_type")
    val paymentType:String,

    @ColumnInfo(name = "customer_name")
    val customerName:String,

    @ColumnInfo(name = "invoice_signature")
    val invoiceSignature:String,

    @ColumnInfo(name = "invoice_signature_date")
    val invoiceSignatureDate: String,


    @ColumnInfo(name = "invoice_total_items")
    val invoiceTotalItems: Int,

    @ColumnInfo(name = "invoice_total_amount")
    val invoiceTotalAmount: Double




)
