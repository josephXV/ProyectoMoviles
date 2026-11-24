package com.example.airapp.data.repository

import com.example.airapp.data.local.dao.ServiceTicketHeaderDao
import com.example.airapp.data.local.entity.ServiceTicketHeaderEntity

class ServiceTicketHeaderRepository(
    private val serviceTicketHeaderDao: ServiceTicketHeaderDao
) {

    /** Devuelve el id generado del tiquete. */
    suspend fun insertTicket(ticket: ServiceTicketHeaderEntity): Long {
        return serviceTicketHeaderDao.insert(ticket)
    }

    suspend fun updateTicket(ticket: ServiceTicketHeaderEntity) {
        serviceTicketHeaderDao.update(ticket)
    }

    suspend fun getAllTickets(): List<ServiceTicketHeaderEntity> {
        return serviceTicketHeaderDao.getAll()
    }

    suspend fun getTicketById(
        ticketId: Long
    ): ServiceTicketHeaderEntity? {
        return serviceTicketHeaderDao.getById(ticketId)
    }

    suspend fun getTicketsByTechnician(
        technicianId: Long
    ): List<ServiceTicketHeaderEntity> {
        return serviceTicketHeaderDao.getByTechnician(technicianId)
    }
}
