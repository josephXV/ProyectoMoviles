package com.example.airapp.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "ticket_details",
    foreignKeys = [
        ForeignKey(
            entity = ServiceTicketHeaderEntity::class,
            parentColumns = ["id"],
            childColumns = ["ticketId"]
        )
    ],
    indices = [
        Index(value = ["ticketId"])
    ]
)
data class TicketDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ticketId: Long,
    val description: String,
    val quantity: Int = 1,
    val unitPrice: Double = 0.0
)
