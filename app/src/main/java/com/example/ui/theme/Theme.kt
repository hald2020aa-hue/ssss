package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class NeonColorSchemeType(val displayNameAr: String, val primaryColor: Color) {
    DYNAMIC("ديناميكية", NeonCyan),
    DARK_BLUE("داكن أزرق", NeonBlue),
    GREEN("أخضر", NeonGreen),
    AMBER("أمبري", NeonAmber),
    PINK("وردي", NeonPink),
    PURPLE("بنفسجي", NeonViolet)
}

fun getNeonColorScheme(schemeType: NeonColorSchemeType = NeonColorSchemeType.DYNAMIC) = darkColorScheme(
    primary = schemeType.primaryColor,
    onPrimary = Color.Black,
    primaryContainer = schemeType.primaryColor.copy(alpha = 0.2f),
    onPrimaryContainer = TextWhite,
    secondary = NeonViolet,
    onSecondary = Color.White,
    background = CyberDarkBg,
    onBackground = TextWhite,
    surface = CyberCardBg,
    onSurface = TextWhite,
    surfaceVariant = CyberCardBgLighter,
    onSurfaceVariant = TextMuted,
    outline = schemeType.primaryColor.copy(alpha = 0.4f)
)

@Composable
fun SSSMangaTheme(
    schemeType: NeonColorSchemeType = NeonColorSchemeType.DYNAMIC,
    content: @Composable () -> Unit
) {
    val colorScheme = getNeonColorScheme(schemeType)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
