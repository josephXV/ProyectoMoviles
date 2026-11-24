package com.example.airapp.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

enum class TicketStatus {
    NUEVO,
    ASIGNADO,
    EN_PROCESO,
    FINALIZADO
}

@Entity(
    tableName = "service_ticket_headers",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"]
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["technicianId"]
        )
    ],
    indices = [
        Index(value = ["ticketNumber"], unique = true),
        Index(value = ["customerId"]),
        Index(value = ["technicianId"])
    ]
)
data class ServiceTicketHeaderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ticketNumber: String,
    val customerId: Long,
    val technicianId: Long,
    val createdAt: Long,
    val status: TicketStatus = TicketStatus.NUEVO,
    val description: String
)
