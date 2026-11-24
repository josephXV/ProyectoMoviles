package com.example.airapp.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.airapp.data.local.entity.ServiceTicketHeaderEntity
import com.example.airapp.data.local.entity.TicketStatus

@Dao
interface ServiceTicketHeaderDao {

    @Insert
    suspend fun insert(ticket: ServiceTicketHeaderEntity): Long

    @Update
    suspend fun update(ticket: ServiceTicketHeaderEntity)

    @Query("SELECT * FROM service_ticket_headers ORDER BY createdAt DESC")
    suspend fun getAll(): List<ServiceTicketHeaderEntity>

    @Query("UPDATE service_ticket_headers SET status = :status WHERE id = :ticketId")
    suspend fun updateStatus(ticketId: Long, status: TicketStatus)

    @Query("SELECT * FROM service_ticket_headers WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): ServiceTicketHeaderEntity?

    @Query("SELECT * FROM service_ticket_headers WHERE ticketNumber = :ticketNumber LIMIT 1")
    suspend fun findByTicketNumber(ticketNumber: String): ServiceTicketHeaderEntity?

    @Query("SELECT * FROM service_ticket_headers WHERE technicianId = :technicianId ORDER BY createdAt DESC")
    suspend fun getByTechnician(technicianId: Long): List<ServiceTicketHeaderEntity>

    @Query("SELECT * FROM service_ticket_headers WHERE customerId = :customerId ORDER BY createdAt DESC")
    suspend fun getByCustomer(customerId: Long): List<ServiceTicketHeaderEntity>

    @Query("SELECT * FROM service_ticket_headers WHERE status = :status ORDER BY createdAt DESC")
    suspend fun getByStatus(status: TicketStatus): List<ServiceTicketHeaderEntity>
}
