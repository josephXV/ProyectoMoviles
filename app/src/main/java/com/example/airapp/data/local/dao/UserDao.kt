package com.example.airapp.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.data.local.entity.UserRole

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY name")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE role = :role AND isActive = 1 ORDER BY name")
    suspend fun getActiveByRole(role: UserRole): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): UserEntity?

    @Query("UPDATE users SET isActive = 0 WHERE id = :userId")
    suspend fun deactivate(userId: Long)

    @Query("UPDATE users SET isActive = 1 WHERE id = :userId")
    suspend fun reactivate(userId: Long)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): UserEntity?
}