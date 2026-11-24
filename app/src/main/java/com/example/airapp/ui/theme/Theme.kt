package com.example.airapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = HvacBlue,
    secondary = HvacCyan,
    background = HvacBackground,
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
    val colorScheme = lightColorScheme(
        primary = Color(0xFF1976D2),
        background = Color(0xFFF7FAFC),
        surface = Color(0xFFF7FAFC)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}