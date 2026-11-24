package com.example.airapp.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.airapp.data.local.entity.ServiceTicketDetailEntity

@Dao
interface ServiceTicketDetailDao {

    @Insert
    suspend fun insert(detail: ServiceTicketDetailEntity): Long

    @Insert
    suspend fun insertAll(details: List<ServiceTicketDetailEntity>)

    @Update
    suspend fun update(detail: ServiceTicketDetailEntity)

    @Delete
    suspend fun delete(detail: ServiceTicketDetailEntity)

    @Query("SELECT COALESCE(SUM(quantity * unitPrice), 0) FROM service_ticket_details WHERE ticketHeaderId = :ticketHeaderId")
    suspend fun getTotal(ticketHeaderId: Long): Long

    @Query("SELECT * FROM service_ticket_details WHERE ticketHeaderId = :ticketHeaderId ORDER BY id")
    suspend fun getByTicket(ticketHeaderId: Long): List<ServiceTicketDetailEntity>
}
