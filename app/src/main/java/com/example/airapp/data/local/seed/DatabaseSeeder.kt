package com.example.airapp.data.local.seed

import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.data.local.entity.UserRole
import com.example.airapp.data.repository.UserRepository
import com.example.airapp.security.PasswordHasher

object DatabaseSeeder {
    suspend fun seedInitialUser(
        userRepository: UserRepository
    ){

        val email = "tech@air.com"

        val existingUser =
            userRepository.findByEmail(email)

        if (existingUser == null) {

            val password = "Tec2026*"

            val passwordHash =
                PasswordHasher.hash(password)

            val user = UserEntity(
                name = "Técnico de prueba",
                email = email,
                phone = "8888-8888",
                passwordHash = passwordHash,
                isActive = true,
                role = UserRole.TECNICO
            )

            userRepository.insertUser(user)
        }
    }
}