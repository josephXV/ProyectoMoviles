package com.example.airapp.data.repository

import com.example.airapp.data.local.dao.UserDao
import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.security.PasswordHasher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }

    suspend fun findByEmail (email: String): UserEntity? {
        return userDao.findByEmail(email)
    }

    suspend fun authenticate(
        email: String,
        password: String
    ): UserEntity? {

        val user = userDao.findByEmail(email)
            ?: return null

        val passwordIsValid =
            withContext(Dispatchers.Default) {
                PasswordHasher.verify(
                    password,
                    user.passwordHash
                )
            }

        return if (passwordIsValid) {
            user
        } else {
            null
        }
    }
}