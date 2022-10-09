package com.gacoca.obr.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gacoca.obr.model.invoice.entities.Invoice
import com.gacoca.obr.model.invoice.entities.InvoiceItem
import com.gacoca.obr.model.invoice.dao.InvoiceDao


@Database(
    entities = [Invoice::class,InvoiceItem::class],
    version = 4,
    exportSchema = true
)
abstract class PosDatabase : RoomDatabase() {

    abstract fun invoiceDao(): InvoiceDao

    companion object{

        private var INSTANCE: PosDatabase? = null

        fun getDatabase(context: Context): PosDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): PosDatabase{

            return Room.databaseBuilder(
                context.applicationContext,
                PosDatabase::class.java,
                "posdb"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        }
    }
}