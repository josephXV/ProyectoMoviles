package com.example.airapp.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.data.local.dao.UserDao


@Database (
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
