package com.example.airapp.ui.viewmodel

import com.example.airapp.data.local.entity.UserEntity

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val validationError: LoginValidationError? = null,
    val authenticationError: LoginAuthenticationError? = null,
    val isAuthenticated: Boolean = false,
    val user: UserEntity? = null,
    val isLoading: Boolean = false
)

enum class LoginValidationError {
    EMAIL_REQUIRED,
    PASSWORD_REQUIRED
}

enum class LoginAuthenticationError {
    INVALID_CREDENTIALS
}

