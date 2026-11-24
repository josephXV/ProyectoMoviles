package com.example.airapp.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.airapp.data.local.dao.CustomerDao
import com.example.airapp.data.local.dao.ServiceTicketHeaderDao
import com.example.airapp.data.local.dao.TicketDetailDao
import com.example.airapp.data.local.dao.UserDao
import com.example.airapp.data.local.entity.CustomerEntity
import com.example.airapp.data.local.entity.ServiceTicketHeaderEntity
import com.example.airapp.data.local.entity.TicketDetailEntity
import com.example.airapp.data.local.entity.UserEntity


@Database (
    entities = [
        UserEntity::class,
        CustomerEntity::class,
        ServiceTicketHeaderEntity::class,
        TicketDetailEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerDao
    abstract fun serviceTicketHeaderDao(): ServiceTicketHeaderDao
    abstract fun ticketDetailDao(): TicketDetailDao
}
