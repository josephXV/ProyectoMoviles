package com.example.airapp.di

import android.content.Context
import com.example.airapp.data.local.database.DatabaseProvider
import com.example.airapp.data.repository.CustomerRepository
import com.example.airapp.data.repository.ServiceTicketDetailRepository
import com.example.airapp.data.repository.ServiceTicketHeaderRepository
import com.example.airapp.data.repository.UserRepository
import com.example.airapp.session.SessionManager

class AppContainer (context: Context)  {
    private val database = DatabaseProvider.getDatabase(context)
    val userRepository = UserRepository(database.userDao())
    val customerRepository = CustomerRepository(database.customerDao())
    val serviceTicketHeaderRepository =
        ServiceTicketHeaderRepository(
            database.serviceTicketHeaderDao()
        )

    val serviceTicketDetailRepository =
        ServiceTicketDetailRepository(
            database.serviceTicketDetailDao()
        )
    val sessionManager = SessionManager()
}