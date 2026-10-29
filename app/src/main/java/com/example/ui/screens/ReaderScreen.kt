package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Brightness6
import androidx.compose.material.icons.outlined.FormatColorText
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MangaItem
import com.example.ui.components.NeonCard
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonViolet
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

data class MangaSpeechBubble(
    val id: String,
    val originalText: String,
    val translatedArabic: String,
    val topPercent: Float,
    val leftPercent: Float,
    val speaker: String
)

@Composable
fun ReaderScreen(
    manga: MangaItem,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentPage by remember { mutableIntStateOf(1) }
    val totalPages = 28
    var showControls by remember { mutableStateOf(true) }
    var isOcrTranslateEnabled by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("اليابانية") }

    // Mock manga speech bubbles for the current page
    val speechBubbles = remember(currentPage) {
        listOf(
            MangaSpeechBubble(
                id = "b1",
                originalText = "何が起きているんだ...？！",
                translatedArabic = "ما الذي يحدث بحق الجحيم...؟! لا يمكن أن يكون هذا حقيقياً!",
                topPercent = 0.22f,
                leftPercent = 0.15f,
                speaker = "البطل"
            ),
            MangaSpeechBubble(
                id = "b2",
                originalText = "力を解放せよ、今こそ立ち上がる時だ！",
                translatedArabic = "أطلق العنان لقوتك الكامنة، حان الوقت لننهض معاً!",
                topPercent = 0.52f,
                leftPercent = 0.25f,
                speaker = "المعلم"
            ),
            MangaSpeechBubble(
                id = "b3",
                originalText = "逃げ道はない！",
                translatedArabic = "لا مهرب لك الآن، المعركة ستحسم هنا!",
                topPercent = 0.75f,
                leftPercent = 0.18f,
                speaker = "العدو"
            )
        )
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFF030712))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { showControls = !showControls }
                    )
                }
        ) {
            // Main Manga Page Canvas
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = if (showControls) 70.dp else 0.dp),
                contentAlignment = Alignment.Center
            ) {
                // Stylized Manga Panel Page Frame
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.94f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF0C1428))
                        .border(1.dp, Color(0xFF1E3A8A), RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Panel 1: Top Wide Action Panel
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFF111D3B), Color(0xFF1A2F5E))
                                )
                            )
                            .border(1.dp, Color(0x6000E5FF), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "صفحة $currentPage • إطار المعركة الكبرى",
                            fontFamily = CairoFontFamily,
                            fontSize = 12.sp,
                            color = NeonCyan.copy(alpha = 0.7f),
                            modifier = Modifier.align(Alignment.TopEnd)
                        )

                        // Speech Bubble 1
                        SpeechBubbleOverlay(
                            bubble = speechBubbles[0],
                            showTranslated = isOcrTranslateEnabled
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Panels 2 & 3: Split row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.3f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Left Panel (Character focus)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFF182242), Color(0xFF101932))
                                    )
                                )
                                .border(1.dp, Color(0x4000E5FF), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            SpeechBubbleOverlay(
                                bubble = speechBubbles[1],
                                showTranslated = isOcrTranslateEnabled
                            )
                        }

                        // Right Panel (Climax Action)
                        Box(
                            modifier = Modifier
                                .weight(1.1f)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFF221A3B), Color(0xFF140F24))
                                    )
                                )
                                .border(1.dp, Color(0x407C4DFF), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            SpeechBubbleOverlay(
                                bubble = speechBubbles[2],
                                showTranslated = isOcrTranslateEnabled
                            )
                        }
                    }
                }
            }

            // Top HUD Controls Bar
            AnimatedVisibility(
                visible = showControls,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .background(Color(0xE6050C1E))
                        .border(
                            width = 0.8.dp,
                            color = Color(0x3000E5FF),
                            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "رجوع",
                                tint = TextWhite
                            )
                        }
                        Column {
                            Text(
                                text = manga.title,
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            Text(
                                text = "الفصل 1 • صفحة $currentPage من $totalPages",
                                fontFamily = CairoFontFamily,
                                fontSize = 11.sp,
                                color = NeonCyan
                            )
                        }
                    }

                    // OCR Live Translation Toggle Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (isOcrTranslateEnabled) Brush.horizontalGradient(
                                    listOf(Color(0xFF2979FF), Color(0xFF7C4DFF))
                                ) else Brush.horizontalGradient(
                                    listOf(Color(0xFF101E3E), Color(0xFF162A55))
                                )
                            )
                            .border(
                                width = 1.2.dp,
                                color = if (isOcrTranslateEnabled) NeonCyan else Color(0x501E88E5),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { isOcrTranslateEnabled = !isOcrTranslateEnabled }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Translate,
                                contentDescription = "ترجمة بالذكاء الاصطناعي",
                                tint = if (isOcrTranslateEnabled) TextWhite else NeonCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isOcrTranslateEnabled) "الترجمة مفعلة" else "ترجمة OCR",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isOcrTranslateEnabled) TextWhite else TextMuted
                            )
                        }
                    }
                }
            }

            // Bottom HUD Controls Bar
            AnimatedVisibility(
                visible = showControls,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .background(Color(0xF0050C1E))
                        .border(
                            width = 0.8.dp,
                            color = Color(0x3000E5FF),
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    // Slider & Page Nav
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (currentPage < totalPages) currentPage++ },
                            enabled = currentPage < totalPages
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.NavigateBefore,
                                contentDescription = "السابق",
                                tint = TextWhite
                            )
                        }

                        Slider(
                            value = currentPage.toFloat(),
                            onValueChange = { currentPage = it.toInt() },
                            valueRange = 1f..totalPages.toFloat(),
                            modifier = Modifier.weight(1f),
                            colors = SliderDefaults.colors(
                                thumbColor = NeonCyan,
                                activeTrackColor = Color(0xFF2979FF),
                                inactiveTrackColor = Color(0xFF132247)
                            )
                        )

                        IconButton(
                            onClick = { if (currentPage > 1) currentPage-- },
                            enabled = currentPage > 1
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.NavigateNext,
                                contentDescription = "التالي",
                                tint = TextWhite
                            )
                        }
                    }

                    // Bottom Quick Icons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        QuickReaderIcon(
                            icon = Icons.Outlined.ZoomIn,
                            label = "تكبير",
                            onClick = { }
                        )
                        QuickReaderIcon(
                            icon = Icons.Outlined.Brightness6,
                            label = "سطوع",
                            onClick = { }
                        )
                        QuickReaderIcon(
                            icon = Icons.Outlined.AutoAwesome,
                            label = "تنقية الصور",
                            onClick = { }
                        )
                        QuickReaderIcon(
                            icon = Icons.Outlined.Settings,
                            label = "الإعدادات",
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpeechBubbleOverlay(
    bubble: MangaSpeechBubble,
    showTranslated: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (showTranslated) Color(0xF50A183C) else Color(0xDDFFFFFF))
            .border(
                width = 1.2.dp,
                color = if (showTranslated) NeonCyan else Color(0xFF222222),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bubble.speaker,
                    fontFamily = CairoFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (showTranslated) NeonCyan else Color(0xFF555555)
                )

                if (showTranslated) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0x3000E5FF))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "مترجم بالعربية (AI OCR)",
                            fontFamily = CairoFontFamily,
                            fontSize = 8.sp,
                            color = NeonCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (showTranslated) bubble.translatedArabic else bubble.originalText,
                fontFamily = CairoFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (showTranslated) TextWhite else Color(0xFF111111),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun QuickReaderIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = TextWhite,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontFamily = CairoFontFamily,
            fontSize = 10.sp,
            color = TextMuted
        )
    }
}
