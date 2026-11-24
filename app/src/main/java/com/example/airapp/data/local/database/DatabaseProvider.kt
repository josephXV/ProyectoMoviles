package com.example.airapp.data.local.database

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver

object DatabaseProvider {
    @Volatile private var INSTANCE: AppDatabase? = null
    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder<AppDatabase>(
                context.applicationContext,
                "app_database.db"
            )
                .setDriver(AndroidSQLiteDriver())
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
            INSTANCE = instance
            instance
        }
    }
}