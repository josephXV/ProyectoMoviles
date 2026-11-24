package com.example.airapp.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "service_ticket_details",
    foreignKeys = [
        ForeignKey(
            entity = ServiceTicketHeaderEntity::class,
            parentColumns = ["id"],
            childColumns = ["ticketHeaderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["ticketHeaderId"])
    ]
)
data class ServiceTicketDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ticketHeaderId: Long,
    val quantity: Int,
    val description: String,
    val unitPrice: Long
)
