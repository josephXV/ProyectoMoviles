package com.example.airapp.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.airapp.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Insert
    suspend fun insert(customer: CustomerEntity): Long

    @Update
    suspend fun update(customer: CustomerEntity)

    @Query("SELECT * FROM customers ORDER BY name")
    suspend fun getAll(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE isActive = 1 ORDER BY name")
    suspend fun getActive(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE isActive = 0 ORDER BY name")
    suspend fun getInactive(): List<CustomerEntity>

    @Query("UPDATE customers SET isActive = 0 WHERE id = :customerId")
    suspend fun deactivate(customerId: Long)

    @Query("UPDATE customers SET isActive = 1 WHERE id = :customerId")
    suspend fun reactivate(customerId: Long)

    @Query("SELECT * FROM customers WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): CustomerEntity?

    @Query("SELECT * FROM customers WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): CustomerEntity?

    @Query("SELECT * FROM customers WHERE name LIKE '%' || :query || '%' ORDER BY name")
    suspend fun searchByName(query: String): List<CustomerEntity>
}
