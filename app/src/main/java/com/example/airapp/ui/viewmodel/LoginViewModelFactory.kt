package com.example.airapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.airapp.data.repository.UserRepository
import kotlin.reflect.KClass

class LoginViewModelFactory (private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(

        modelClass: KClass<T>,

        extras: CreationExtras

    ): T {

        if (modelClass == LoginViewModel::class) {

            @Suppress("UNCHECKED_CAST")

            return LoginViewModel(userRepository) as T

        }

        throw IllegalArgumentException(

            "Clase ViewModel desconocida"

        )

    }
}