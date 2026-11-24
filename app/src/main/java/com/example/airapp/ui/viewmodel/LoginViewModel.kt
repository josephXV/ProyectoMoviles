package com.example.airapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.airapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class LoginViewModel (private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password)
        }
    }

    fun validateInput() {

        val currentState = _uiState.value

        val validationError = when {
            currentState.email.isBlank() ->
                LoginValidationError.EMAIL_REQUIRED

            currentState.password.isBlank() ->
                LoginValidationError.PASSWORD_REQUIRED

            else -> null
        }

        if (validationError != null) {
            _uiState.update {
                it.copy(
                    validationError = validationError,
                    authenticationError = null
                )
            }

            return
        }

        viewModelScope.launch {

            val user = withContext(Dispatchers.Default) {
                userRepository.authenticate(
                    currentState.email,
                    currentState.password
                )
            }

            if (user != null) {
                _uiState.update {
                    it.copy(
                        validationError = null,
                        authenticationError = null,
                        isAuthenticated = true
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        validationError = null,
                        authenticationError =
                            LoginAuthenticationError.INVALID_CREDENTIALS,
                        isAuthenticated = false
                    )
                }
            }
        }
    }
}