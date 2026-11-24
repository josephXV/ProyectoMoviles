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
            childColumns = ["customerId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["assignedTechnicianId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["customerId"]),
        Index(value = ["assignedTechnicianId"])
    ]
)
data class ServiceTicketHeaderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long,
    val serviceAddress: String,
    val assignedTechnicianId: Long? = null,
    val reportedProblem: String,
    val status: TicketStatus = TicketStatus.NUEVO,
    val createdAt: Long
)
