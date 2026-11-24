package com.example.airapp

import android.app.Application
import com.example.airapp.data.local.seed.DatabaseSeeder
import com.example.airapp.di.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AirAppApplication: Application() {
    val appContainer: AppContainer by lazy {
        AppContainer(this)
    }

    private val applicationScope =
        CoroutineScope(
            SupervisorJob() + Dispatchers.IO
        )

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            DatabaseSeeder.seedInitialData(
                appContainer.userRepository,
                appContainer.customerRepository
            )
        }
    }
}