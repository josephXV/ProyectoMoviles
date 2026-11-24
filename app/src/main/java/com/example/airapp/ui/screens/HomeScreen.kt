package com.example.airapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.airapp.R
import com.example.airapp.data.local.entity.UserEntity
import com.example.airapp.data.local.entity.UserRole

@Composable
fun HomeScreen(
    user: UserEntity,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_welcome, user.name),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(
                if (user.role == UserRole.ADMIN) R.string.role_admin else R.string.role_technician
            ),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onLogout) {
            Text(stringResource(R.string.home_logout))
        }
    }
}
