package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Neon Cyberpunk Dark Theme Colors
val CyberDarkBg = Color(0xFF050B1B)
val CyberDarkBgSecondary = Color(0xFF091228)
val CyberCardBg = Color(0xD90C1838)
val CyberCardBgLighter = Color(0xE6102048)

// Neon Accents
val NeonCyan = Color(0xFF00F0FF)
val NeonBlue = Color(0xFF2979FF)
val NeonViolet = Color(0xFF8B5CF6)
val NeonPink = Color(0xFFFF2A85)
val NeonGreen = Color(0xFF00E676)
val NeonAmber = Color(0xFFFFB300)
val NeonRed = Color(0xFFFF3366)

// Borders & Glows
val NeonCyanGlow = Color(0x6600F0FF)
val NeonBlueGlow = Color(0x662979FF)
val NeonVioletGlow = Color(0x668B5CF6)
val NeonBorderBlue = Color(0x661E88E5)
val NeonBorderSubtle = Color(0x3300F0FF)

// Texts
val TextWhite = Color(0xFFFFFFFF)
val TextMuted = Color(0xFF8E9EB8)
val TextDark = Color(0xFF556885)

// Gradients
val PrimaryNeonGradient = Brush.horizontalGradient(
    listOf(Color(0xFF0052D4), Color(0xFF4364F7), Color(0xFF6FB1FC))
)

val ActiveTabGradient = Brush.verticalGradient(
    listOf(Color(0xFF1E3A8A), Color(0xFF1D4ED8))
)

val ActiveButtonGradient = Brush.horizontalGradient(
    listOf(Color(0xFF2979FF), Color(0xFF8B5CF6))
)

val CardGlowGradient = Brush.verticalGradient(
    listOf(Color(0x3300F0FF), Color(0x0500F0FF))
)
