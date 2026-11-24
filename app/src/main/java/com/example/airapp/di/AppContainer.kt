package com.example.airapp.di

import android.content.Context
import com.example.airapp.data.local.database.DatabaseProvider
import com.example.airapp.data.repository.UserRepository

class AppContainer (context: Context)  {
    private val database = DatabaseProvider.getDatabase(context)
    val userRepository = UserRepository(database.userDao())
}