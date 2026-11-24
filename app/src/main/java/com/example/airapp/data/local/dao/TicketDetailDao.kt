package com.example.airapp.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.airapp.data.local.entity.TicketDetailEntity

@Dao
interface TicketDetailDao {

    @Insert
    suspend fun insert(detail: TicketDetailEntity): Long

    @Insert
    suspend fun insertAll(details: List<TicketDetailEntity>)

    @Update
    suspend fun update(detail: TicketDetailEntity)

    @Delete
    suspend fun delete(detail: TicketDetailEntity)

    @Query("SELECT COALESCE(SUM(quantity * unitPrice), 0.0) FROM ticket_details WHERE ticketId = :ticketId")
    suspend fun getTotal(ticketId: Long): Double

    @Query("SELECT * FROM ticket_details WHERE ticketId = :ticketId ORDER BY id")
    suspend fun getByTicket(ticketId: Long): List<TicketDetailEntity>
}
