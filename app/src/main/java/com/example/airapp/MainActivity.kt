package com.example.airapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.airapp.ui.screens.HomeScreen
import com.example.airapp.ui.screens.LoginRoute
import com.example.airapp.ui.theme.AirAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appContainer = (application as AirAppApplication).appContainer
        setContent {
            AirAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val user by appContainer.sessionManager.currentUser
                        .collectAsStateWithLifecycle()
                    val currentUser = user
                    if (currentUser == null) {
                        LoginRoute(
                            userRepository = appContainer.userRepository,
                            onLoginSuccess = appContainer.sessionManager::login,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        HomeScreen(
                            user = currentUser,
                            onLogout = appContainer.sessionManager::logout,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}



