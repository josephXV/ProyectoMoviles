package com.example.airapp.session

import com.example.airapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Sesión en memoria del usuario autenticado. */
class SessionManager {
    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser.asStateFlow()

    fun login(user: UserEntity) { _currentUser.value = user }
    fun logout() { _currentUser.value = null }
}
