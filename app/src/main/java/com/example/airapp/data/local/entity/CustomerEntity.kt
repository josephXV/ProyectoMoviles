package com.example.airapp.data.local.entity

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [
        Index(
            value = ["email"],
            unique = true
        )
    ]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val isActive: Boolean = true
)
