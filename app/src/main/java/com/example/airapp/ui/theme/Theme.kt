package com.example.airapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = HvacDarkPrimary,
    secondary = HvacCyan,
    background = HvacDarkBackground,
    surface = HvacDarkSurface,
    onPrimary = HvacTextDark,
    onBackground = HvacDarkText,
    onSurface = HvacDarkText
)

private val LightColorScheme = lightColorScheme(
    primary = HvacBlue,
    secondary = HvacLightBlue,
    tertiary = HvacCyan,
    background = HvacBackground,
    surface = HvacSurface,
    surfaceVariant = HvacCardBackground,
    onPrimary = HvacTextLight,
    onBackground = HvacTextDark,
    onSurface = HvacTextDark
)

@Composable
fun AirAppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}