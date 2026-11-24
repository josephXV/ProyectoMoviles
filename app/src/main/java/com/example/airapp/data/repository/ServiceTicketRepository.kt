package com.example.airapp.data.repository

import com.example.airapp.data.local.dao.ServiceTicketHeaderDao
import com.example.airapp.data.local.dao.TicketDetailDao
import com.example.airapp.data.local.entity.ServiceTicketHeaderEntity
import com.example.airapp.data.local.entity.TicketDetailEntity
import com.example.airapp.data.local.entity.TicketStatus

class ServiceTicketRepository(
    private val headerDao: ServiceTicketHeaderDao,
    private val detailDao: TicketDetailDao
) {
    suspend fun createTicket(
        header: ServiceTicketHeaderEntity,
        details: List<TicketDetailEntity> = emptyList()
    ): Long {
        val ticketId = headerDao.insert(header)
        if (details.isNotEmpty()) {
            detailDao.insertAll(details.map { it.copy(ticketId = ticketId) })
        }
        return ticketId
    }

    suspend fun update(ticket: ServiceTicketHeaderEntity) = headerDao.update(ticket)
    suspend fun updateStatus(ticketId: Long, status: TicketStatus) =
        headerDao.updateStatus(ticketId, status)

    suspend fun getAll() = headerDao.getAll()
    suspend fun findById(id: Long) = headerDao.findById(id)
    suspend fun getByTechnician(technicianId: Long) = headerDao.getByTechnician(technicianId)
    suspend fun getByCustomer(customerId: Long) = headerDao.getByCustomer(customerId)
    suspend fun getByStatus(status: TicketStatus) = headerDao.getByStatus(status)

    suspend fun addDetail(detail: TicketDetailEntity): Long = detailDao.insert(detail)
    suspend fun updateDetail(detail: TicketDetailEntity) = detailDao.update(detail)
    suspend fun deleteDetail(detail: TicketDetailEntity) = detailDao.delete(detail)
    suspend fun getDetails(ticketId: Long) = detailDao.getByTicket(ticketId)
    suspend fun getTotal(ticketId: Long): Double = detailDao.getTotal(ticketId)
}
