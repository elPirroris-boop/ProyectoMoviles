package com.example.gastos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Gasto::class], version = 1)
abstract class GastosDatabase : RoomDatabase() {

    abstract fun gastoDao(): GastoDao

    companion object {
        @Volatile
        private var INSTANCE: GastosDatabase? = null

        fun getDatabase(context: Context): GastosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GastosDatabase::class.java,
                    "gastos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}