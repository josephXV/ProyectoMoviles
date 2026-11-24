package com.example.airapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.airapp.data.local.entity.UserEntity
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.airapp.ui.viewmodel.LoginViewModel
import com.example.airapp.ui.viewmodel.LoginUiState
import com.example.airapp.ui.viewmodel.LoginValidationError
import com.example.airapp.data.repository.UserRepository
import com.example.airapp.ui.viewmodel.LoginViewModelFactory
import com.example.airapp.ui.viewmodel.LoginAuthenticationError
import com.example.airapp.R
import com.example.airapp.ui.theme.AirAppTheme

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    userRepository: UserRepository,
    onLoginSuccess: (UserEntity) -> Unit = {}
){
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(userRepository))
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.user) {
        uiState.user?.let(onLoginSuccess)
    }
    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onEmailChange = {
            viewModel.onEmailChange(it)
        },
        onPasswordChange = {
            viewModel.onPasswordChange(it)
        },
        onLoginClick = {
            viewModel.validateInput()
        }
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState = LoginUiState(),
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {}
    )

{
    val validationMessage = when (uiState.validationError) {
        LoginValidationError.EMAIL_REQUIRED ->
            stringResource(R.string.login_username_required)

        LoginValidationError.PASSWORD_REQUIRED ->
            stringResource(R.string.login_password_required)

        null -> null
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.login_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                )

                Text(
                    text = stringResource(R.string.login_card_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    label = {
                        Text(
                            text = stringResource(
                                R.string.login_username_label
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    label = {
                        Text(
                            text = stringResource(
                                R.string.login_password_label
                            )
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                if (
                    uiState.authenticationError ==
                    LoginAuthenticationError.INVALID_CREDENTIALS
                ) {
                    Text(
                        text = stringResource(
                            R.string.login_invalid_credentials
                        ),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = onLoginClick,
                    enabled = !uiState.isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login_button)
                    )
                }

                if (validationMessage != null) {
                    Text(
                        text = validationMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (uiState.isAuthenticated) {
                    Text(
                        text = stringResource(
                            R.string.login_success
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {

    AirAppTheme {
        LoginScreen()
    }
}