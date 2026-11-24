package com.example.airapp.data.local.seed

import com.example.airapp.data.local.entity.CustomerEntity
import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.data.local.entity.UserRole
import com.example.airapp.data.repository.CustomerRepository
import com.example.airapp.data.repository.UserRepository
import com.example.airapp.security.PasswordHasher

/** Datos de prueba para desarrollo. Cambiar las claves antes de publicar. */
object DatabaseSeeder {

    suspend fun seedInitialData(
        userRepository: UserRepository,
        customerRepository: CustomerRepository
    ) {
        seedUser(
            userRepository = userRepository,
            name = "Administrador",
            email = "admin@air.com",
            phone = "8888-0000",
            password = "Admin2026*",
            role = UserRole.ADMIN
        )

        seedUser(
            userRepository = userRepository,
            name = "Jhon Doe",
            email = "tech@air.com",
            phone = "8888-8888",
            password = "Tec2026*",
            role = UserRole.TECNICO
        )

        seedUser(
            userRepository = userRepository,
            name = "Richard Stallman",
            email = "tech2@air.com",
            phone = "8888-7777",
            password = "Tec2026*",
            role = UserRole.TECNICO
        )

        if (customerRepository.getAllCustomers().isEmpty()) {

            customerRepository.insertCustomer(
                CustomerEntity(
                    name = "Carlos Rodríguez",
                    phone = "8700-1000",
                    email = "carlos@gmail.com",
                    address = "Ciudad Quesada",
                    isActive = true
                )
            )

            customerRepository.insertCustomer(
                CustomerEntity(
                    name = "María López",
                    phone = "8700-2000",
                    email = "maria@gmail.com",
                    address = "Florencia",
                    isActive = true
                )
            )

            customerRepository.insertCustomer(
                CustomerEntity(
                    name = "Empresa ABC",
                    phone = "8700-3000",
                    email = "contacto@abc.com",
                    address = "Aguas Zarcas",
                    isActive = true
                )
            )
        }
    }

    private suspend fun seedUser(
        userRepository: UserRepository,
        name: String,
        email: String,
        phone: String,
        password: String,
        role: UserRole
    ) {

        val existingUser =
            userRepository.findByEmail(email)

        if (existingUser == null) {

            val passwordHash =
                PasswordHasher.hash(password)

            val user = UserEntity(
                name = name,
                email = email,
                phone = phone,
                passwordHash = passwordHash,
                isActive = true,
                role = role
            )

            userRepository.insertUser(user)
        }
    }
}
