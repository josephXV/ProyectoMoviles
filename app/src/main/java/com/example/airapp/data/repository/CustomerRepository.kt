package com.example.airapp.data.repository

import com.example.airapp.data.local.dao.CustomerDao
import com.example.airapp.data.local.entity.CustomerEntity

class CustomerRepository(private val customerDao: CustomerDao) {

    suspend fun insert(customer: CustomerEntity): Long {
        return customerDao.insert(customer)
    }

    suspend fun update(customer: CustomerEntity) {
        customerDao.update(customer)
    }

    suspend fun getAll(): List<CustomerEntity> {
        return customerDao.getAll()
    }

    suspend fun getActive(): List<CustomerEntity> {
        return customerDao.getActive()
    }

    suspend fun getInactive(): List<CustomerEntity> {
        return customerDao.getInactive()
    }

    suspend fun deactivate(customerId: Long) {
        customerDao.deactivate(customerId)
    }

    suspend fun reactivate(customerId: Long) {
        customerDao.reactivate(customerId)
    }

    suspend fun findById(id: Long): CustomerEntity? {
        return customerDao.findById(id)
    }

    suspend fun findByEmail(email: String): CustomerEntity? {
        return customerDao.findByEmail(email)
    }

    suspend fun searchByName(query: String): List<CustomerEntity> {
        return customerDao.searchByName(query)
    }
}
