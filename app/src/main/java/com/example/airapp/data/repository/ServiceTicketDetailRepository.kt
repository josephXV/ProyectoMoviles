package com.example.airapp.data.repository

import com.example.airapp.data.local.dao.ServiceTicketDetailDao
import com.example.airapp.data.local.entity.ServiceTicketDetailEntity

class ServiceTicketDetailRepository(
    private val serviceTicketDetailDao: ServiceTicketDetailDao
) {

    /** Devuelve el id generado del detalle. */
    suspend fun insertDetail(detail: ServiceTicketDetailEntity): Long {
        return serviceTicketDetailDao.insert(detail)
    }

    suspend fun updateDetail(detail: ServiceTicketDetailEntity) {
        serviceTicketDetailDao.update(detail)
    }

    suspend fun deleteDetail(detail: ServiceTicketDetailEntity) {
        serviceTicketDetailDao.delete(detail)
    }

    suspend fun getDetailsByTicket(
        ticketHeaderId: Long
    ): List<ServiceTicketDetailEntity> {
        return serviceTicketDetailDao.getByTicket(ticketHeaderId)
    }
}
