package com.gacoca.obr.model.shopconfig.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taxconfig")
data class TaxConfig(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "taxIdNumber")
    var taxIdNumber: String,

    @ColumnInfo(name = "registryNumber")
    var registryNumber: String,

    @ColumnInfo(name = "fiscalCenter")
    var fiscalCenter: String,

    @ColumnInfo(name = "SubjectVat")
    var subjectVat: Boolean,
    @ColumnInfo(name = "vatPercentage")
    var vatPercentage: Double


)
